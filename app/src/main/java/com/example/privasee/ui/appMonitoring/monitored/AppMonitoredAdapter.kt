package com.example.privasee.ui.appMonitoring.monitored

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.privasee.database.model.Restriction
import com.example.privasee.databinding.RecyclerItemAppCbBinding

class AppMonitoredAdapter: RecyclerView.Adapter<AppMonitoredAdapter.AppViewHolder>() {

    inner class AppViewHolder(val binding: RecyclerItemAppCbBinding): RecyclerView.ViewHolder(binding.root)
    private var monitoredList = emptyList<Restriction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemAppCbBinding.inflate(layoutInflater, parent, false)
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return monitoredList.size
    }

}