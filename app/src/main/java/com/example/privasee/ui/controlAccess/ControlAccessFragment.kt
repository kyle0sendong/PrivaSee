package com.example.privasee.ui.controlAccess

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.privasee.AppAccessService
import com.example.privasee.databinding.FragmentControlAccessBinding


class ControlAccessFragment : Fragment() {

    private var _binding: FragmentControlAccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlAccessBinding.inflate(inflater, container, false)

        binding.btnTestService1.setOnClickListener {
            val intent = Intent(requireContext(), AppAccessService::class.java)
            intent.putExtra("key", "lock")
            requireContext().startService(intent)
        }
        binding.btnTestService2.setOnClickListener {
            val intent = Intent(requireContext(), AppAccessService::class.java)
            intent.putExtra("key", "removeLock")
            requireContext().startService(intent)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}