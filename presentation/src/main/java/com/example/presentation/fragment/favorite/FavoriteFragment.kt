package com.example.presentation.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.fragment.main.MainViewModel
import com.example.presentation.fragment.main.adapter.CourseAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: CourseAdapter
    private lateinit var favoritesStorage: FavoritesStorage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesStorage = FavoritesStorage(requireContext())
        val recyclerView = view.findViewById<RecyclerView>(R.id.favoritesRecyclerView)

        adapter = CourseAdapter(emptyList(), favoritesStorage)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.courses.collect { allCourses ->
                val favoriteIds = favoritesStorage.getAllFavs()
                val favoriteCourses = allCourses.filter { favoriteIds.contains(it.id.toString()) }
                adapter.updateData(favoriteCourses)
            }
        }
    }
}