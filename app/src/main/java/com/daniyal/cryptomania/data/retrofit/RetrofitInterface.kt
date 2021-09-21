package com.daniyal.cryptomania.data.retrofit

import com.daniyal.cryptomania.data.model.CryptoRates
import com.daniyal.cryptomania.utilities.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET(Constants.CryptoRatesEndPoint)
    suspend fun getTodo() : Response<List<CryptoRates>>;

}