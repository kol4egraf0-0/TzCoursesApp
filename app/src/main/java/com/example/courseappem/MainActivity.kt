package com.example.courseappem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.courseappem.databinding.ActivityMainBinding
import com.example.presentation.fragment.favorite.FavoriteFragment
import com.example.presentation.fragment.main.MainFragment
import com.example.presentation.fragment.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, MainFragment())
                .commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                com.example.presentation.R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, MainFragment())
                        .commit()
                    print("home selected")
                    true
                }
                com.example.presentation.R.id.menu_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, FavoriteFragment())
                        .commit()
                    print("fav selected")
                    true
                }
                com.example.presentation.R.id.menu_account -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, ProfileFragment())
                        .commit()
                    print("accc selected")
                    true
                }
                else -> false
            }
        }
    }
}