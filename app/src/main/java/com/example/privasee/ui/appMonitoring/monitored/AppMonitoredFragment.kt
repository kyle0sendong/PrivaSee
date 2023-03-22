package com.example.privasee.ui.appMonitoring.monitored

import android.os.Bundle
import android.util.Log
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
import com.example.privasee.databinding.FragmentAppMonitoredBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AppMonitoredFragment : Fragment() {

    private var _binding: FragmentAppMonitoredBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestrictionViewModel: RestrictionViewModel
    private var ownerId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppMonitoredBinding.inflate(inflater, container, false)

        // Recyclerview adapter
        val adapter = AppMonitoredAdapter()
        binding.rvAppMonitored.adapter = adapter
        binding.rvAppMonitored.layoutManager = LinearLayoutManager(requireContext())

        // Database queries
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]

        lifecycleScope.launch(Dispatchers.IO) {
            // for testing only. test owner
            ownerId = mUserViewModel.getOwnerId(isOwner = true)

        }

        mRestrictionViewModel.getAllMonitoredApps(ownerId).observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        // Buttons
        binding.btnDisable0.isEnabled = false
        binding.btnUnmonitoredList.setOnClickListener {
            findNavController().navigate(R.id.action_appMonitoredFragment_to_appUnmonitoredFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}