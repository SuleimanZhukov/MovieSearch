package com.example.moviesearch.di

import com.example.moviesearch.framework.ui.main.MainViewModel
import com.example.moviesearch.model.repository.Repository
import com.example.moviesearch.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }
}