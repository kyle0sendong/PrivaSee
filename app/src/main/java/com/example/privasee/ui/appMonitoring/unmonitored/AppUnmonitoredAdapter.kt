package com.example.privasee.ui.appMonitoring.unmonitored

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.privasee.database.model.Restriction
import com.example.privasee.databinding.FragmentAppUnmonitoredBinding

class AppUnmonitoredAdapter: RecyclerView.Adapter<AppUnmonitoredAdapter.AppViewHolder>() {

    inner class AppViewHolder(val binding: FragmentAppUnmonitoredBinding): RecyclerView.ViewHolder(binding.root)
    private var unmonitoredList = emptyList<Restriction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentAppUnmonitoredBinding.inflate(layoutInflater, parent, false)
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return unmonitoredList.size
    }
}