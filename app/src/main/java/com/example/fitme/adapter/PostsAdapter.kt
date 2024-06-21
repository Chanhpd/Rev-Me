package com.example.fitme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.model.Posts
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PostsAdapter(val list: ArrayList<Posts>) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val posts = list[position]
        holder.username.text = posts.userName
        holder.tvDate.text = posts.postDate
        holder.tvContent.text = posts.postDescription
        Picasso.get().load(posts.postImage).into(holder.imgPost)
        Picasso.get().load(posts.userImage).into(holder.imgAvatar)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.tv_user_name)
        val imgAvatar: CircleImageView = itemView.findViewById(R.id.imageView_avatar)
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        val tvContent = itemView.findViewById<TextView>(R.id.tv_desc)
        val imgPost = itemView.findViewById<ImageView>(R.id.img_post)
        val edtComment = itemView.findViewById<TextView>(R.id.edtComment)
    }
}