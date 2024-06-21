package com.example.fitme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.model.Program
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.squareup.picasso.Picasso

class ProgramAdapter(val list: ArrayList<Program>) :
    RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_program, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = list[position]
        holder.title.text = program.title
        holder.desc.text = program.description
        holder.progress.progress = program.progress.toFloat()
        Picasso.get().load(program.image).into(holder.image)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.textView_name_program)
        val image = itemView.findViewById<ImageView>(R.id.imageView_program)
        val progress = itemView.findViewById<CircularProgressBar>(R.id.progressBar_program)
        val desc = itemView.findViewById<TextView>(R.id.textView_Desc)


    }
}