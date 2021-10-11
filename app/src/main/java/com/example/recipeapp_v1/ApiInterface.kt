package com.example.recipeapp_v1

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


//https://dojo-recipes.herokuapp.com/recipes/

interface ApiInterface {

   @GET("recipes/")

    suspend fun getData(): Response<List<Data>>


    @POST("recipes/")


    fun postData(@Body data: Data): Call<Data>

}