package com.daniyal.cryptomania.data.retrofit

import retrofit2.http.GET

interface RetrofitInterface {

    @GET("todo")
    suspend fun getTodo();
}