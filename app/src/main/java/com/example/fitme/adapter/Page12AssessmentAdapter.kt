package com.example.fitme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R

class Page12AssessmentAdapter(
    val context: Context,
    val list: List<String>,
    val selectedItems: MutableList<String>
) : RecyclerView.Adapter<Page12AssessmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ass12_supp, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.checkBox.text = item
        holder.checkBox.isChecked = selectedItems.contains(item)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (!selectedItems.contains(item)) {
                    selectedItems.add(item)
                }
            } else {
                selectedItems.remove(item)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox_ass12)
    }
}