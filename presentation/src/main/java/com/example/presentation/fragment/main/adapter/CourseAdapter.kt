package com.example.presentation.fragment.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.viewmodels.entity.Course
import com.example.presentation.R
import com.example.presentation.fragment.favorite.FavoritesStorage

class CourseAdapter(private var items: List<Course>,
                    private val favoritesStorage: FavoritesStorage) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private var isSortedByDate = false

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.titleCourse)
        private val text: TextView = view.findViewById(R.id.textCourse)
        private val price: TextView = view.findViewById(R.id.priceCourse)
        private val rating: TextView = view.findViewById(R.id.textRate)
        private val startDate: TextView = view.findViewById(R.id.dateStartCourse)
        private val imageView: ImageView = view.findViewById(R.id.courseImage)
        private val bookmarkIcon : ImageButton = view.findViewById(R.id.bookmarkIcon)

        fun bind(course: Course) {
            title.text = course.title
            text.text = course.text
            price.text = "${course.price}₽"
            rating.text = course.rate.toString()
            startDate.text = course.startDate

            Glide.with(itemView.context)
                .load(android.R.drawable.ic_delete)
                .into(imageView)

            val isFavorite = favoritesStorage.isFav(course.id.toString())
            bookmarkIcon.setColorFilter(
                ContextCompat.getColor(itemView.context,
                    if (isFavorite) R.color.green else R.color.white)
            )
            bookmarkIcon.isSelected = isFavorite

            bookmarkIcon.setOnClickListener {
                bookmarkIcon.isSelected = !bookmarkIcon.isSelected
                if (bookmarkIcon.isSelected) {
                    favoritesStorage.addToFav(course.id.toString())
                    bookmarkIcon.setColorFilter(ContextCompat.getColor(itemView.context, R.color.green))
                } else {
                    favoritesStorage.removeFav(course.id.toString())
                    bookmarkIcon.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateData(newCourses: List<Course>) {
        items = newCourses
        notifyDataSetChanged()
    }

    fun sortByDate() {
        isSortedByDate = !isSortedByDate
        items = if (isSortedByDate) {
            items.sortedBy { it.startDate }
        } else {
            items.shuffled()
        }
        notifyDataSetChanged()
    }
}