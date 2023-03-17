package com.example.privasee.ui.appRestriction.blocklist

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.privasee.database.model.Restriction
import com.example.privasee.databinding.RecyclerItemAppRestrictionBinding

class AppBlocklistAdapter(): RecyclerView.Adapter<AppBlocklistAdapter.AppViewHolder>() {

    inner class AppViewHolder(val binding: RecyclerItemAppRestrictionBinding): RecyclerView.ViewHolder(binding.root)
    private var restrictionList = emptyList<Restriction>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return restrictionList.size
    }
}