package com.example.privasee.ui.user.userSettings.userAppControl.uncontrolled

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.privasee.databinding.FragmentUserAppUncontrolledBinding

class UserAppUncontrolledFragment : Fragment() {

    private var _binding: FragmentUserAppUncontrolledBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserAppUncontrolledBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}