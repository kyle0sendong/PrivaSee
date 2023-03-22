package com.example.privasee.ui.appMonitoring.monitored

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.privasee.database.model.App
import com.example.privasee.database.model.Restriction
import com.example.privasee.databinding.RecyclerItemAppCbBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppMonitoredAdapter: RecyclerView.Adapter<AppMonitoredAdapter.AppViewHolder>() {

    inner class AppViewHolder(val binding: RecyclerItemAppCbBinding): RecyclerView.ViewHolder(binding.root)
    private var monitoredList = emptyList<Restriction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemAppCbBinding.inflate(layoutInflater, parent, false)

        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val appName = monitoredList[position].appName
        val isChecked = monitoredList[position].monitored
        holder.binding.apply {
            tvAppName.text = appName
            cbRestrict.isChecked = isChecked
        }
    }

    override fun getItemCount(): Int {
        return monitoredList.size
    }

    fun setData(data: List<Restriction>) {
        this.monitoredList = data
        notifyDataSetChanged()
    }

}