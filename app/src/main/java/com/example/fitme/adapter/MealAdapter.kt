package com.example.fitme.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.model.Meal
import com.example.fitme.view.detail.MealDetailActivity
import com.squareup.picasso.Picasso

class MealAdapter(val list: ArrayList<Meal>) :
    RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        Picasso.get().load(item.image_url).into(holder.img)
        holder.name.text = item.name
        holder.calo.text = item.calories.toString() + " cal"
        holder.desc.text = item.description
        holder.fat.text = item.fat.toString() + "g"
        holder.carb.text = item.carbs.toString() + "g"
        holder.protein.text = item.protein.toString() + "g"



        if(item.status){
            holder.status.background = holder.itemView.context.resources.getDrawable(R.drawable.bg_17_border_complete)
            holder.status.text = "Completed"
        }else{
            holder.status.background = holder.itemView.context.resources.getDrawable(R.drawable.bg_17_border_app)
            holder.status.text = "UnCompleted"
        }


        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MealDetailActivity::class.java)
            intent.putExtra("meal", item)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img : ImageView = itemView.findViewById(R.id.meal_image)
        val name : TextView = itemView.findViewById(R.id.meal_name)
        val calo : TextView = itemView.findViewById(R.id.meal_calo)
        val desc : TextView = itemView.findViewById(R.id.meal_desc)

        val fat : TextView = itemView.findViewById(R.id.meal_fat)
        val carb : TextView = itemView.findViewById(R.id.meal_carb)
        val protein : TextView = itemView.findViewById(R.id.meal_protein)
        val status : TextView = itemView.findViewById(R.id.meal_status)
    }
}