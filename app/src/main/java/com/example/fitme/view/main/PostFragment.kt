package com.example.fitme.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme.R
import com.example.fitme.adapter.PostsAdapter
import com.example.fitme.model.Posts

class PostFragment : Fragment() {

    lateinit var recycleViewPost: RecyclerView
    lateinit var postAdapter: PostsAdapter
    lateinit var postList: ArrayList<Posts>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)

        recycleViewPost = view.findViewById(R.id.recycleViewPost)
        postList = ArrayList()
        addData()
        recycleViewPost.adapter = PostsAdapter(postList)
        recycleViewPost.layoutManager = LinearLayoutManager(context)

        return view
    }

    fun addData() {
        postList.add(
            Posts(
                1,
                "“Its gonna hurt so bad that you’ll do it again and again. because your kinda....”",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1JOpazl77-lm41imrLDON9g5AEHdCBxIWo3BYCp7Zgw&s",
                "12 hours",
                "1",
                "1",
                "John wick",
                "https://down-vn.img.susercontent.com/file/96fdcd65ef075b128f36c289a62723db"
            )
        )
        postList.add(
            Posts(
                2,
                "Hơn nhau ở pha cân 5",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZSinBcyKXn9uxs3GnGg9C2lPWVkoUYC9G9Qtl4O4avw&s",
                "12 hours",
                "1",
                "1",
                "John wick",
                "https://img.hoidap247.com/picture/question/20210531/large_1622431721191.jpg"
            )
        )
    }
}