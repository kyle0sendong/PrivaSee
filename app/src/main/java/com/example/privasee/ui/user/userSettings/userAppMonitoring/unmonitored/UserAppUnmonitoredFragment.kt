package com.example.privasee.ui.user.userSettings.userAppMonitoring.unmonitored

import android.annotation.SuppressLint
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
import com.example.privasee.databinding.FragmentUserAppUnmonitoredBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserAppUnmonitoredFragment : Fragment() {

    private var _binding: FragmentUserAppUnmonitoredBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestrictionViewModel: RestrictionViewModel

    private var ownerId: Int = 0

    @SuppressLint("DetachAndAttachSameFragment")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserAppUnmonitoredBinding.inflate(inflater, container, false)

        // Recyclerview adapter
        val adapter = UserAppUnmonitoredAdapter()
        binding.rvAppUnmonitored.adapter = adapter
        binding.rvAppUnmonitored.layoutManager = LinearLayoutManager(requireContext())

        // Database queries
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]


        lifecycleScope.launch(Dispatchers.IO) {

            ownerId = mUserViewModel.getOwnerId(isOwner = true)
            val restrictionList = mRestrictionViewModel.getAllUnmonitoredApps(ownerId)

            withContext(Dispatchers.Main) {
                restrictionList.observe(viewLifecycleOwner, Observer {
                    adapter.setData(it)
                })
            }
        }

        // Buttons
        binding.btnDisable1.isEnabled = false
        binding.btnMonitoredList.setOnClickListener {
            findNavController().navigate(R.id.action_appUnmonitoredFragment_to_appMonitoredFragment)
        }

        // Update new list of monitored apps
        binding.btnApplyUnmonitored.setOnClickListener {
            val newMonitoredList = adapter.getCheckedApps()
            lifecycleScope.launch(Dispatchers.IO) {
                for (restrictionId in newMonitoredList)
                    mRestrictionViewModel.updateMonitoredApps(restrictionId, true)
            }
            if (newMonitoredList.isNotEmpty())
                findNavController().navigate(R.id.action_appUnmonitoredFragment_to_appMonitoredFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}