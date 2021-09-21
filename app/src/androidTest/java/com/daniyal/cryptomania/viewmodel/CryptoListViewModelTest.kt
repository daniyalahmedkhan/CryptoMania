package com.daniyal.cryptomania.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.daniyal.cryptomania.data.model.CryptoRates
import com.daniyal.cryptomania.data.repo.CryptoRatesRepo
import com.daniyal.cryptomania.data.repo.remote.ResponseEvent
import com.daniyal.cryptomania.data.retrofit.RetrofitInterface
import com.daniyal.cryptomania.utilities.Constants
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.Assert.*
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.CountDownLatch
import javax.inject.Inject


@RunWith(AndroidJUnit4ClassRunner::class)
class CryptoListViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var cryptoRates: List<CryptoRates> = emptyList()
    lateinit var data_observer: Observer<ResponseEvent<List<CryptoRates>>>
    lateinit var cryptoListViewModel: CryptoListViewModel
    lateinit var baseApiInterface: RetrofitInterface

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        baseApiInterface = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build().create(RetrofitInterface::class.java)
        val cryptoRatesRepo = CryptoRatesRepo(baseApiInterface)
        cryptoListViewModel = CryptoListViewModel(cryptoRatesRepo, context)
    }

    @After
    fun tearDown() {
        cryptoListViewModel.itemState.removeObserver(data_observer)

    }


    // Testing ViewModel
    @Test
    @Throws(InterruptedException::class)
    fun cryptoListNotEmpty() {

        cryptoListViewModel.getCryptoRates()
        val signal = CountDownLatch(1)

        data_observer = Observer<ResponseEvent<List<CryptoRates>>> { datastate ->
// once the data change has occured , latch should be decremented
            when (datastate) {


                is ResponseEvent.Success<List<CryptoRates>> -> {
                    cryptoRates = datastate.data!!
                    signal.countDown()

                    Assert.assertTrue(cryptoRates.count() > 0)
                }


            }


        }
        cryptoListViewModel.itemState.observeForever(data_observer)
        signal.await()
    }


}