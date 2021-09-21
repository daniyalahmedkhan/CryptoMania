package com.daniyal.cryptomania.data.model

import com.google.gson.annotations.SerializedName

data class CryptoRates(

    @SerializedName("symbol") val symbol: String,
    @SerializedName("price") val price: String,

    )