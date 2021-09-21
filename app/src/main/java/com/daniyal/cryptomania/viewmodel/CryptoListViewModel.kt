package com.daniyal.cryptomania.viewmodel


import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniyal.cryptomania.data.model.CryptoRates
import com.daniyal.cryptomania.data.repo.CryptoRatesRepo
import com.daniyal.cryptomania.data.repo.remote.ResponseEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoListViewModel @ViewModelInject constructor(
    private val cryptoRepo: CryptoRatesRepo,
    @ApplicationContext val context: Context
) : ViewModel() {

    val itemState: MutableLiveData<ResponseEvent<CryptoRates>> = MutableLiveData()


    private fun getCryptoRates() {
        itemState.value = ResponseEvent.Loading
        viewModelScope.launch {
            Dispatchers.IO
            try {
//                val response = todoItemRepo.postItemToServer(todoItemResponse)
//                if (response.isSuccessful) {
//                    itemState.value = ResponseEvent.Success(response.body())
//                } else {
//                    val jObjError = JSONObject(response.errorBody()!!.string())
//                    val message = GeneralHelper.parseFailureJson((jObjError))
//                    itemState.value = ResponseEvent.Failure(message)
//                }

            } catch (e: Exception) {
                itemState.value = ResponseEvent.Failure(e.message)
            }
        }
    }

}