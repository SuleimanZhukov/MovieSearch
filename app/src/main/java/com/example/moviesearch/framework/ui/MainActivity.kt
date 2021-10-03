package com.example.moviesearch.framework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesearch.R
import com.example.moviesearch.framework.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, MainFragment.newInstance())
                .commitNow()
        }
    }
}