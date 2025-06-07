package com.example.courseappem

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

            window.navigationBarColor = ContextCompat.getColor(this, com.example.presentation.R.color.dark_gray)
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR

        binding.bottomNavigation.onItemSelected = { position ->
            when (position) {
                0 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, MainFragment())
                        .commit()
                    true
                }
                1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, FavoriteFragment())
                        .commit()
                    true
                }
                2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}