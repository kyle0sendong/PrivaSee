package com.example.privasee.ui.appMonitoring.unmonitored

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.privasee.database.model.Restriction
import com.example.privasee.databinding.FragmentAppUnmonitoredBinding
import com.example.privasee.databinding.RecyclerItemAppCbBinding

class AppUnmonitoredAdapter: RecyclerView.Adapter<AppUnmonitoredAdapter.AppViewHolder>() {

    inner class AppViewHolder(val binding: RecyclerItemAppCbBinding): RecyclerView.ViewHolder(binding.root)
    private var unmonitoredList = emptyList<Restriction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemAppCbBinding.inflate(layoutInflater, parent, false)
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val appName = unmonitoredList[position].appName
        val isChecked = unmonitoredList[position].monitored
        holder.binding.apply {
            tvAppName.text = appName
            cbRestrict.isChecked = isChecked
        }

    }

    override fun getItemCount(): Int {
        return unmonitoredList.size
    }

    fun setData(data: List<Restriction>) {
        this.unmonitoredList = data
    }
}