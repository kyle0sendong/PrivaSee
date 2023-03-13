package com.example.privasee.ui.initialRun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.privasee.R
import com.example.privasee.databinding.FragmentSetupPermissionsBinding


class SetupPermissionsFragment : Fragment() {

    private var _binding: FragmentSetupPermissionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetupPermissionsBinding.inflate(inflater, container, false)

        binding.btnPermissionsNext.setOnClickListener {
            findNavController().navigate(R.id.action_setupPermissionsFragment_to_setupOwnerFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}