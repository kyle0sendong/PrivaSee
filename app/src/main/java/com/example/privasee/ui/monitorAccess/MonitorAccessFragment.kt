package com.example.privasee.ui.monitorAccess

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.privasee.databinding.FragmentMonitorAccessBinding
import com.example.privasee.ui.userList.userInfoUpdate.userAppControl.UserAppControllingActivity
import com.example.privasee.ui.userList.userInfoUpdate.userAppMonitoring.UserAppMonitoringActivity

class MonitorAccessFragment : Fragment() {

    private var _binding: FragmentMonitorAccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonitorAccessBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}