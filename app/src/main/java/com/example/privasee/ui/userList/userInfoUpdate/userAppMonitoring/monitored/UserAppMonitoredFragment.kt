package com.example.privasee.ui.userList.userInfoUpdate.userAppMonitoring.monitored

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.privasee.AppAccessService
import com.example.privasee.R
import com.example.privasee.database.viewmodel.AppViewModel
import com.example.privasee.database.viewmodel.RestrictionViewModel
import com.example.privasee.databinding.FragmentUserAppMonitoredBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserAppMonitoredFragment : Fragment() {

    private var _binding: FragmentUserAppMonitoredBinding? = null
    private val binding get() = _binding!!

    private lateinit var mRestrictionViewModel: RestrictionViewModel
    private lateinit var mAppViewModel: AppViewModel

    private val args: UserAppMonitoredFragmentArgs by navArgs()

    private var job1: Job? = null
    private var job2: Job? = null
    private var job3: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserAppMonitoredBinding.inflate(inflater, container, false)

        // Recyclerview adapter
        val adapter = UserAppMonitoredAdapter()
        binding.rvAppMonitored.adapter = adapter
        binding.rvAppMonitored.layoutManager = LinearLayoutManager(requireContext())

        // Database view-models
        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]
        mAppViewModel = ViewModelProvider(this)[AppViewModel::class.java]

        // Nav arguments
        val userId = args.userId
        val bundle = Bundle()
        bundle.putInt("userId", userId)

        // Observe Live data of monitored app list
        job1 = lifecycleScope.launch {
            val monitoredList = mRestrictionViewModel.getAllMonitoredApps(userId)
            withContext(Dispatchers.Main) {
                monitoredList.observe(viewLifecycleOwner, Observer {
                    adapter.setData(it)
                })
            }
        }

        // Navigating button
        binding.btnUnmonitoredList.setOnClickListener {
            findNavController().navigate(R.id.action_appMonitoredFragment_to_appUnmonitoredFragment, bundle)
        }

        // Update new list of monitored apps
        binding.btnApplyMonitored.setOnClickListener {

            val newRestriction = adapter.getCheckedApps()
            job2 = lifecycleScope.launch(Dispatchers.IO) {
                for (restrictionId in newRestriction)
                    mRestrictionViewModel.updateMonitoredApps(restrictionId, false)
            }

            if (newRestriction.isNotEmpty()) {
                // Send data to Accessibility Service on monitoring
                job3 = lifecycleScope.launch(Dispatchers.IO) {
                    val newMonitoredListPackageName: MutableList<String> = mutableListOf()
                    val newMonitoredListAppName: MutableList<String> = mutableListOf()
                    for (restrictionId in newRestriction) {
                        val appId = mRestrictionViewModel.getPackageId(restrictionId)
                        newMonitoredListPackageName.add(mAppViewModel.getPackageName(appId))
                        newMonitoredListAppName.add(mAppViewModel.getAppName(appId))
                    }
                    val intent = Intent(requireContext(), AppAccessService::class.java)
                    intent.putExtra("action", "removeMonitor" )
                    intent.putStringArrayListExtra("removeMonitoredAppPackageName", ArrayList(newMonitoredListPackageName))
                    intent.putStringArrayListExtra("removeMonitoredAppName", ArrayList(newMonitoredListAppName))
                    requireContext().startService(intent)
                }

                findNavController().navigate(
                    R.id.action_appMonitoredFragment_to_appUnmonitoredFragment,
                    bundle
                )
            }

        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        job1?.cancel()
        job2?.cancel()
        job3?.cancel()
        _binding = null
    }

}