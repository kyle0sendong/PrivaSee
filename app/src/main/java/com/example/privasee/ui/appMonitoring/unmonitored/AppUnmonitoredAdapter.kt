package com.example.privasee.ui.appMonitoring.unmonitored

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.privasee.database.model.Restriction
import com.example.privasee.databinding.RecyclerItemAppCbBinding
import kotlinx.android.synthetic.main.recycler_item_app_cb.view.*

class AppUnmonitoredAdapter(): RecyclerView.Adapter<AppUnmonitoredAdapter.AppViewHolder>() {

    inner class AppViewHolder(val binding: RecyclerItemAppCbBinding): RecyclerView.ViewHolder(binding.root)

    private var unmonitoredList = emptyList<Restriction>()
    private val checkedApps = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemAppCbBinding.inflate(layoutInflater, parent, false)
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {

        holder.binding.apply {
            val appName = unmonitoredList[position].appName
            val isChecked = unmonitoredList[position].monitored
            holder.binding.apply {
                tvAppName.text = appName
                cbRestrict.isChecked = isChecked
            }
        }

        holder.binding.cbRestrict.setOnCheckedChangeListener { _, isChecked ->
            val restrictionId = this.unmonitoredList[position].id
            if (isChecked) {
                checkedApps.add(restrictionId)
            }
        }

    }

    fun setData(data: List<Restriction>) {
        this.unmonitoredList = data
        notifyItemInserted(unmonitoredList.size-1)
    }

    override fun getItemCount(): Int {
        return unmonitoredList.size
    }

    fun getCheckedApps(): List<Int> {
        return this.checkedApps
    }
}