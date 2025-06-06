package com.example.courseappem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.courseappem.databinding.ActivityMainBinding
import com.example.presentation.fragment.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

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
                    true
                }
                else -> false
            }
        }
    }
}