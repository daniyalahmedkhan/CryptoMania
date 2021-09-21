package com.daniyal.cryptomania.data.model

import com.google.gson.annotations.SerializedName

data class CryptoRates(

    @SerializedName("success") val success: Boolean,
    @SerializedName("terms") val terms: String,
    @SerializedName("privacy") val privacy: String,
    @SerializedName("timestamp") val timestamp: Int,
    @SerializedName("target") val target: String,
    @SerializedName("rates") val rates: CryptoRatesList
)