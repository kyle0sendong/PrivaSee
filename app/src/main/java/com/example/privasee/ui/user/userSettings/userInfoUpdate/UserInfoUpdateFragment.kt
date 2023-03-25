package com.example.privasee.ui.user.userSettings.userInfoUpdate

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.privasee.R
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.UserViewModel
import com.example.privasee.databinding.FragmentUserInfoUpdateBinding

class UserInfoUpdateFragment : Fragment(), MenuProvider {

    private var _binding: FragmentUserInfoUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    private val args by navArgs<UserInfoUpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoUpdateBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.updateName.setText(args.currentUser.name)

        binding.updateButton.setOnClickListener {
            updateItem()
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }


    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.delete_menu) {
            deleteUser()
            return true
        }
        return false
    }


    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.name}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateUserFragment_to_userFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.name}?")
        builder.create().show()
    }


    private fun updateItem(){
        val name = binding.updateName.text.toString()

        if(checkInput(name)) {
            val updatedUser = User(args.currentUser.id, name, args.currentUser.isOwner)
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated $name", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateUserFragment_to_userFragment)
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