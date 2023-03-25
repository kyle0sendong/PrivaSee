package com.example.privasee.ui.user.userSettings.userAppControl.controlled

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
import com.example.privasee.databinding.FragmentUserAppControlledBinding
import com.example.privasee.ui.user.userSettings.userAppMonitoring.monitored.UserAppMonitoredAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserAppControlledFragment : Fragment() {

    private var _binding: FragmentUserAppControlledBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestrictionViewModel: RestrictionViewModel
    private var ownerId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserAppControlledBinding.inflate(inflater, container, false)

        // Recyclerview adapter
        val adapter = UserAppMonitoredAdapter()
        binding.rvAppControlled.adapter = adapter
        binding.rvAppControlled.layoutManager = LinearLayoutManager(requireContext())

        // Database queries
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]

        lifecycleScope.launch(Dispatchers.IO) {
            ownerId = mUserViewModel.getOwnerId(isOwner = true)
            val controlledList = mRestrictionViewModel.getAllControlledApps(ownerId)
            withContext(Dispatchers.Main) {
                controlledList.observe(viewLifecycleOwner, Observer {
                    adapter.setData(it)
                })
            }
        }

        // Buttons
        binding.btnUncontrolledList.setOnClickListener {
            findNavController().navigate(R.id.action_userAppControlledFragment_to_userAppUncontrolledFragment)
        }

        // Update new list of monitored apps
        binding.btnApplyControlled.setOnClickListener {
            val newUncontrolledList = adapter.getCheckedApps()
            lifecycleScope.launch(Dispatchers.IO) {
                for (restrictionId in newUncontrolledList)
                    mRestrictionViewModel.updateControlledApps(restrictionId, false)
            }
            if (newUncontrolledList.isNotEmpty())
                findNavController().navigate(R.id.action_userAppControlledFragment_to_userAppUncontrolledFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}