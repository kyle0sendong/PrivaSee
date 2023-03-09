package com.example.privasee.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.privasee.R
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserBinding.inflate(inflater, container, false)

        // Recyclerview
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        binding.btnAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_addUserFragment)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}