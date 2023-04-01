package com.example.privasee.ui.controlAccess

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.privasee.AppAccessService
import com.example.privasee.R
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.FragmentControlAccessBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ControlAccessFragment : Fragment() {

    private var _binding: FragmentControlAccessBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlAccessBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]



        lifecycleScope.launch {
            val userList = mUserViewModel.getAllDataLive
            withContext(Dispatchers.Main) { // Show user list using drop down spinner
                userList.observe(viewLifecycleOwner) {

                    val spinner = binding.root.findViewById<Spinner>(R.id.spinnerUsers)

                    // Show all user using names.
                    val spinnerAdapter = object : ArrayAdapter<User> (requireContext(), R.layout.spinner_item_user, it) {
                        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                            val view = super.getView(position, convertView, parent)
                            val user = getItem(position)
                            if (user != null)
                                (view.findViewById<TextView>(android.R.id.text1)).text = user.name
                            return view
                        }

                        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                            val view = if (convertView == null) {
                                val inflater = LayoutInflater.from(context)
                                inflater.inflate(R.layout.spinner_item_user, parent, false)
                            } else
                                convertView
                            val user = getItem(position)
                            if (user != null)
                                (view.findViewById<TextView>(android.R.id.text1)).text = user.name

                            return view
                        }
                    }

                    spinner.adapter = spinnerAdapter
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                            val selectedUser = parent.getItemAtPosition(position) as User
                            val selectedUserName = selectedUser.name
                            binding.tvTest123.text = selectedUser.toString()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            // Do nothing
                        }
                    }
                }
            }
        }

        binding.btnTestService1.setOnClickListener {
            val intent = Intent(requireContext(), AppAccessService::class.java)
            intent.putExtra("controlAction", "addLock")
            requireContext().startService(intent)
        }
        binding.btnTestService2.setOnClickListener {
            val intent = Intent(requireContext(), AppAccessService::class.java)
            intent.putExtra("controlAction", "removeLock")
            requireContext().startService(intent)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}