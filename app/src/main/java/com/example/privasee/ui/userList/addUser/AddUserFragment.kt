package com.example.privasee.ui.userList.addUser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.privasee.R
import com.example.privasee.database.model.Restriction
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.AppViewModel
import com.example.privasee.database.viewmodel.RestrictionViewModel
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.FragmentAddUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mAppViewModel: AppViewModel
    private lateinit var mRestrictionViewModel: RestrictionViewModel

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mAppViewModel = ViewModelProvider(this)[AppViewModel::class.java]
        mRestrictionViewModel = ViewModelProvider(this)[RestrictionViewModel::class.java]

        binding.btnNext.setOnClickListener {
            val name = binding.etAddUserName.text.toString()
            val user = User(0, name, false)

            if (checkInput(name)) {
                job = lifecycleScope.launch(Dispatchers.IO) {
                    mUserViewModel.addUser(user)
                    val latestUser = mUserViewModel.getLastInsertedUser()
                    val appList = mAppViewModel.getAllData()
                    for (app in appList) {

                        val restriction: Restriction = if (app.appName == "Facebook") { // test default monitored
                            Restriction(0, app.appName, monitored = true, controlled = false, latestUser.id, app.id)
                        } else {
                            Restriction(0, app.appName, monitored = false, controlled = false, latestUser.id, app.id)
                        }

                        Log.d("test123", "$restriction")
                        mRestrictionViewModel.addRestriction(restriction)
                    }
                }
                findNavController().navigate(R.id.action_addUserFragment_to_userFragment)
            } else
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
        }

        return binding.root
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
        job?.cancel()
        _binding = null
    }
}