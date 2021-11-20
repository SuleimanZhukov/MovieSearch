package com.example.moviesearch.framework.services

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.example.moviesearch.framework.ui.details.*
import com.example.moviesearch.model.restentities.MovieDTO
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MovieLoaderService : Service(), CoroutineScope by MainScope() {
    override fun onBind(p0: Intent?): IBinder? = null

    private val broadcastIntent = Intent(DETAILS_INTENT_FILTER)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        launch(Dispatchers.IO) {
            loadMovie()
        }
        return START_NOT_STICKY
    }

    fun loadMovie(): MovieDTO? {
//        val title = name.replace(' ', '+')
        val title = "Jack+Reacher:+Never+Go+Back"
        try {
            val uri = URL(
                "https://api.themoviedb.org/3/search/movie?api_key=9404872446d5cae8afbefd38b24ce36a&language=ru&query=${title}"
            )

            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
//                urlConnection.addRequestProperty("x-themoviedb-api-key", "9404872446d5cae8afbefd38b24ce36a&language=ru&query=${title}")
                urlConnection.readTimeout = 10000

                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))

                val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    getLinesForOld(reader)
                } else {
                    getLines(reader)
                }
                return Gson().fromJson(lines, MovieDTO::class.java)

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun getLinesForOld(reader: BufferedReader): String {
        val rawData = StringBuilder(1024)
        var tempVeriable: String?

        while (reader.readLine().also { tempVeriable = it } != null) {
            rawData.append(tempVeriable).append("\n")
        }
        reader.close()
        return rawData.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun onResponse(movieDTO: MovieDTO) {
        val results = movieDTO.results
        onSuccessResponse(
            results[0]!!.original_title,
            results[0]!!.title,
            results[0]!!.release_date,
            results[0]!!.vote_average,
            results[0]!!.overview
        )
    }

    private fun onSuccessResponse(original_title: String, title: String, release_date: String, vote_average: Float, overview: String) {
        broadcastIntent.putExtra(DETAILS_ORIGINAL_TITLE, original_title)
        broadcastIntent.putExtra(DETAILS_RUSSIAN_TITLE, title)
        broadcastIntent.putExtra(DETAILS_RELEASE_DATE, release_date)
        broadcastIntent.putExtra(DETAILS_RATING, vote_average)
        broadcastIntent.putExtra(DETAILS_DESCRIPTION, overview)
        sendBroadcast(broadcastIntent)
    }

//    companion object {
//        fun start(context: Context) {
//            val connectivityServiceIntent = Intent(context, MovieLoaderService::class.java)
//            context.startService(connectivityServiceIntent)
//        }
//
//        fun stop(context: Context) {
//            val connectivityServiceIntent = Intent(context, MovieLoaderService::class.java)
//            context.stopService(connectivityServiceIntent)
//        }
//    }
}