package com.example.moviesearch.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.moviesearch.model.restentities.MovieDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object MovieLoader {

    fun loadMovie(title: String): MovieDTO {
        try {
            val uri = URL("https://api.themoviedb.org/3/search/movie?api_key=")

            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.addRequestProperty("API Key (v3 auth)", "9404872446d5cae8afbefd38b24ce36a&language=ru&query=${title}")
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
}