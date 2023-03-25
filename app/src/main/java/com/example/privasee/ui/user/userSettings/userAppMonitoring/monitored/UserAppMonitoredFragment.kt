package com.example.privasee.ui.user.userSettings.userAppMonitoring.monitored

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.privasee.R
import com.example.privasee.database.viewmodel.RestrictionViewModel
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.FragmentUserAppMonitoredBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserAppMonitoredFragment : Fragment() {

    private var _binding: FragmentUserAppMonitoredBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestrictionViewModel: RestrictionViewModel
    private var ownerId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserAppMonitoredBinding.inflate(inflater, container, false)

        // Recyclerview adapter
        val adapter = UserAppMonitoredAdapter()
        binding.rvAppMonitored.adapter = adapter
        binding.rvAppMonitored.layoutManager = LinearLayoutManager(requireContext())

        // Database queries
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]

        lifecycleScope.launch(Dispatchers.IO) {

            ownerId = mUserViewModel.getOwnerId(isOwner = true)
            val restrictionList = mRestrictionViewModel.getAllMonitoredApps(ownerId)

            withContext(Dispatchers.Main) {
                restrictionList.observe(viewLifecycleOwner, Observer {
                    adapter.setData(it)
                })
            }
        }

        // Buttons
        binding.btnDisable0.isEnabled = false

        // Navigate to Unmonitored List
        binding.btnUnmonitoredList.setOnClickListener {
            findNavController().navigate(R.id.action_appMonitoredFragment_to_appUnmonitoredFragment)
        }

        // Update new list of monitored apps
        binding.btnApplyMonitored.setOnClickListener {
            val newUnmonitoredList = adapter.getCheckedApps()
            lifecycleScope.launch(Dispatchers.IO) {
                for (restrictionId in newUnmonitoredList)
                    mRestrictionViewModel.updateMonitoredApps(restrictionId, false)
            }
            if (newUnmonitoredList.isNotEmpty())
                findNavController().navigate(R.id.action_appMonitoredFragment_to_appUnmonitoredFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}