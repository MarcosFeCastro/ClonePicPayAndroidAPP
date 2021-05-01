package br.com.app.picpayclone.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val URL = "http://192.168.1.67:8080"

object RetrofitService {

    val instance = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    inline fun <reified T> create() = instance.create(T::class.java)

}