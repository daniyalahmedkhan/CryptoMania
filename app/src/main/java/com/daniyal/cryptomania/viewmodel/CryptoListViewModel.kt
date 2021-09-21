package com.daniyal.cryptomania.viewmodel


import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniyal.cryptomania.data.model.CryptoRates
import com.daniyal.cryptomania.data.repo.CryptoRatesRepo
import com.daniyal.cryptomania.data.repo.remote.ResponseCodeHandle
import com.daniyal.cryptomania.data.repo.remote.ResponseEvent
import com.daniyal.cryptomania.utilities.GeneralHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class CryptoListViewModel @ViewModelInject constructor(
    var cryptoRepo: CryptoRatesRepo,
    @ApplicationContext val context: Context
) : ViewModel() {

    val itemState: MutableLiveData<ResponseEvent<List<CryptoRates>>> = MutableLiveData()


    fun getCryptoRates() {
        requestCryptoRates()
    }


    private fun requestCryptoRates() {
        itemState.value = ResponseEvent.Loading
        viewModelScope.launch {
            Dispatchers.IO
            try {
                val response = cryptoRepo.getCryptoRatesFromServer()
                if (response.isSuccessful) {
                    itemState.value = ResponseEvent.Success(response.body())
                } else {

                    itemState.value = ResponseEvent.Failure(
                        ResponseCodeHandle.extractResponseCode(
                            response,
                            context
                        )
                    )
                }

            } catch (e: Exception) {
                itemState.value = ResponseEvent.Failure(e.message)
            }
        }
    }

}