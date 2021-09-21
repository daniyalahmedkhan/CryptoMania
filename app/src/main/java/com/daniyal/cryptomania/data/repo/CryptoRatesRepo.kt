package com.daniyal.cryptomania.data.repo

import com.daniyal.cryptomania.data.model.CryptoRates
import com.daniyal.cryptomania.data.retrofit.RetrofitInterface
import com.daniyal.cryptomania.utilities.Constants
import retrofit2.Response
import javax.inject.Inject

open class CryptoRatesRepo @Inject constructor(private val baseApiInterface: RetrofitInterface) {

    suspend fun getCryptoRatesFromServer(): Response<List<CryptoRates>> {
        return baseApiInterface.getTodo()
    }

}