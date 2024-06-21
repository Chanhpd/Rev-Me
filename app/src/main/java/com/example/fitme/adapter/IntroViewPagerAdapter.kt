package com.example.fitme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.model.IntroSlider

class IntroViewPagerAdapter(val context: Context, val list: ArrayList<IntroSlider>) :
    RecyclerView.Adapter<PagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_intro_viewpager2, parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = list[position]

        holder.itemView.apply {
            holder.title.text = item.title
            holder.description.text = item.description
            holder.image.setImageResource(item.image)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.txtTitleIntro)
    val description = itemView.findViewById<TextView>(R.id.txtDescIntro)
    val image = itemView.findViewById<ImageView>(R.id.imgIntro)
}


