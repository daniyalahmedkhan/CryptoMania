package com.daniyal.cryptomania.data.repo.remote

import android.content.Context
import com.daniyal.cryptomania.R
import retrofit2.Response

object ResponseCodeHandle {

    fun <T> extractResponseCode(response: Response<T>, context: Context): String? {
        return when (response.code()) {
            400 -> context.resources.getString(R.string.badRequestError)
            401 -> context.resources.getString(R.string.unauthorizedError)
            403 -> context.resources.getString(R.string.forbiddenError)
            404 -> context.resources.getString(R.string.notFound)
            500 -> context.resources.getString(R.string.serverError)
            502 -> context.resources.getString(R.string.gatewayBadError)
            504 -> context.resources.getString(R.string.gatewayTimeoutError)
            else -> context.resources.getString(R.string.generalError)
        }
    }
}