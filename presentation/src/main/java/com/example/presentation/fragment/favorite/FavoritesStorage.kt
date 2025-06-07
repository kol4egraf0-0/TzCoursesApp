package com.example.presentation.fragment.favorite

import android.content.Context

class FavoritesStorage(context: Context)  { //заменить на room
    private val prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)

    fun addToFav(courseId: String) {
        prefs.edit().putBoolean(courseId, true).apply()
    }

    fun removeFav(courseId: String) {
        prefs.edit().remove(courseId).apply()
    }

    fun isFav(courseId: String): Boolean {
        return prefs.getBoolean(courseId, false)
    }

    fun getAllFavs(): Set<String> {
        return prefs.all.keys
    }
}