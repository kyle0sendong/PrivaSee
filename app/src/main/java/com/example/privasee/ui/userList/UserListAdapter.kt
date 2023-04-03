package com.example.privasee.ui.userList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.privasee.database.model.User
import com.example.privasee.database.viewmodel.RestrictionViewModel
import com.example.privasee.databinding.RecyclerItemUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserListAdapter(): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: RecyclerItemUserBinding): RecyclerView.ViewHolder(binding.root)
    private var userList = emptyList<User>()
    private lateinit var mRestrictionViewModel: RestrictionViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemUserBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]

        holder.binding.apply {
            tvUserName.text = currentUser.name
            val owner = "Owner"

            if (currentUser.isOwner)
                tvIsOwner.text = owner
            else
                tvIsOwner.isVisible = false

            Log.d("tagimandos", "userlistadapter $currentUser")
            recyclerItemUser.setOnClickListener {
                val action = UserListFragmentDirections.actionUserFragmentToUpdateUserFragment(currentUser)
                recyclerItemUser.findNavController().navigate(action)
            }
        }

    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyItemInserted(userList.size-1)
    }

}