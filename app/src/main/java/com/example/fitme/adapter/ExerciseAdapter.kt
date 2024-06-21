package com.example.fitme.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.model.Exercise
import com.example.fitme.view.detail.ExerciseVideoActivity
import com.example.fitme.view.detail.WorkoutDetailActivity
import com.squareup.picasso.Picasso

class ExerciseAdapter(val list: ArrayList<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.num.text ="Exercise "+ item.id
        holder.title.text = item.name
        holder.time.text = item.duration_minutes.toString() + " min"
        Picasso.get().load(item.image_url).error(R.drawable.woman_running).into(holder.img)

        if(item.status){
            holder.status.background = holder.itemView.context.resources.getDrawable(R.drawable.bg_17_border_complete)
            holder.status.text = "Completed"
        }else{
            holder.status.background = holder.itemView.context.resources.getDrawable(R.drawable.bg_17_border_app)
            holder.status.text = "UnCompleted"
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WorkoutDetailActivity::class.java)
            intent.putExtra("exercise", item)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val num: TextView = itemView.findViewById(R.id.textView_numEx)
        val title: TextView = itemView.findViewById(R.id.textView_title_ex)
        val time = itemView.findViewById<TextView>(R.id.textView_time_ex)
        val img = itemView.findViewById<ImageView>(R.id.imageView_thumb_ex)
        val status = itemView.findViewById<TextView>(R.id.tv_status)
    }
}