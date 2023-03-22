package com.example.privasee.ui.appMonitoring.unmonitored

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.privasee.R
import com.example.privasee.database.viewmodel.RestrictionViewModel
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.FragmentAppUnmonitoredBinding
import com.example.privasee.ui.appMonitoring.monitored.AppMonitoredAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AppUnmonitoredFragment : Fragment() {

    private var _binding: FragmentAppUnmonitoredBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestrictionViewModel: RestrictionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppUnmonitoredBinding.inflate(inflater, container, false)

        // Recyclerview adapter
        val adapter = AppUnmonitoredAdapter()
        binding.rvAppUnmonitored.adapter = adapter
        binding.rvAppUnmonitored.layoutManager = LinearLayoutManager(requireContext())

        // Database queries
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]
        lifecycleScope.launch(Dispatchers.IO) {
            // for testing only. test owner
            val ownerId = mUserViewModel.getOwnerId(isOwner = true)
            val unmonitoredList = mRestrictionViewModel.getAllUnmonitoredApps(ownerId)
            adapter.setData(unmonitoredList)
        }

        binding.btnDisable1.isEnabled = false
        binding.btnMonitoredList.setOnClickListener {
            findNavController().navigate(R.id.action_appUnmonitoredFragment_to_appMonitoredFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}