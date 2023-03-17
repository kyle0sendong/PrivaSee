package com.example.privasee.ui.appMonitoring.monitored

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.privasee.R
import com.example.privasee.databinding.FragmentAppMonitoredBinding


class AppMonitoredFragment : Fragment() {

    private var _binding: FragmentAppMonitoredBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppMonitoredBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}