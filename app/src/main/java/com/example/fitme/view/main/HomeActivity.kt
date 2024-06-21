package com.example.fitme.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitme.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        intView()
        replaceFragment(HomeFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu_feed -> {
                    replaceFragment(FeedFragment())
                    true
                }
                R.id.menu_food -> {
                    replaceFragment( FoodFragment())
                    true
                }
                R.id.menu_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

    }

    fun intView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view_main)
    }


    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_main, fragment)
        transaction.commit()
    }

}