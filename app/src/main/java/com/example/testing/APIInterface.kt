package com.example.testing


import retrofit2.Call
import retrofit2.http.GET


interface APIInterface {

    @GET("customer")
    fun getData() : Call<List<Customer>>

}
