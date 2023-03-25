package com.example.privasee.ui.user.userList.addUser

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.privasee.R
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.FragmentAddUserBinding


class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.btnNext.setOnClickListener {
            insertToDatabase()
            Intent(requireContext(), AddUserCapturePhoto::class.java).also {
                startActivity(it)
            }
        }

        return binding.root
    }

    private fun insertToDatabase() {
        val name = binding.etAddUserName.text.toString()

        if (checkInput(name)) {
            val user = User(0, name, true)
            mUserViewModel.addUser(user)
            findNavController().navigate(R.id.action_addUserFragment_to_userFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkInput(name: String): Boolean {
        return name.isNotEmpty()
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    override fun onPause() {
        super.onPause()
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}