package com.example.presentation.fragment.main.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.viewmodels.entity.Course
import com.example.presentation.R

class CourseAdapter(private var items: List<Course>) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.titleCourse)
        private val text: TextView = view.findViewById(R.id.textCourse)
        private val price: TextView = view.findViewById(R.id.priceCourse)
        private val rating: RatingBar = view.findViewById(R.id.ratingBar)
        private val startDate: TextView = view.findViewById(R.id.dateStartCourse)

        fun bind(course: Course) {
            title.text = course.title
            text.text = course.text
            price.text = "${course.price}₽"
            rating.rating = course.rate.toFloat()
            startDate.text = course.startDate
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
}