package com.example.moviesearch.framework.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesearch.AppState
import com.example.moviesearch.model.entities.NowPlayingMovie
import com.example.moviesearch.model.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getMovies() = getDataFromLocalSource()

    fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
//            liveDataToObserve.postValue(AppState.Success(repository.getMoviesFromServer()))
        }
    }
}