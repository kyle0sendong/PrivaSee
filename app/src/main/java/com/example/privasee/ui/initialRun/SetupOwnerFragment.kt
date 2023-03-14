package com.example.privasee.ui.initialRun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.privasee.R
import com.example.privasee.databinding.FragmentSetupOwnerBinding


class SetupOwnerFragment : Fragment() {

    private var _binding: FragmentSetupOwnerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetupOwnerBinding.inflate(inflater, container, false)

        binding.btnSetupOwnerFinish.setOnClickListener {
            findNavController().navigate(R.id.action_setupOwnerFragment_to_mainActivity)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}