package com.example.moviesearch.framework.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesearch.AppState
import com.example.moviesearch.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(title: String) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val data = repository.getTheMovieFromServer(title)
            liveDataToObserve.postValue(AppState.Success(listOf(data)))
        }.start()
    }
}