package com.example.retrofitexample

import retrofit2.http.GET

interface ApiInterface {
    @GET("marvel")
    fun getHeroes(): retrofit2.Call<List<Question>>

}