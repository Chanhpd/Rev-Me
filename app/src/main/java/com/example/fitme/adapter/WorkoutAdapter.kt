package com.example.fitme.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.model.Workout
import com.example.fitme.view.detail.WorkoutDetailActivity
import com.squareup.picasso.Picasso

class WorkoutAdapter(val list: ArrayList<Workout>) :
    RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = list[position]
        holder.title.text = workout.title
        Picasso.get().load(workout.image).into(holder.image)
        holder.numOfExercise.text = workout.numOfExercise
        holder.numberPerform.text = workout.numberPerform
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WorkoutDetailActivity::class.java)
            intent.putExtra("workout", workout)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.textView_workout)
        val numOfExercise = itemView.findViewById<TextView>(R.id.textView_num_total)
        val image = itemView.findViewById<ImageView>(R.id.imgeView_workout)
        val numberPerform = itemView.findViewById<TextView>(R.id.textView_reps)


    }
}