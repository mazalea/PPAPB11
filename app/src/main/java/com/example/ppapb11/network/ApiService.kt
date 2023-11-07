package com.example.ppapb11.network

import com.example.ppapb11.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("employees")
    fun getAllUsers(): Call<Users>
}