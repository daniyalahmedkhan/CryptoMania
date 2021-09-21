package com.daniyal.cryptomania.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daniyal.cryptomania.R
import com.daniyal.cryptomania.databinding.CryptoListFragmentBinding
import com.daniyal.cryptomania.ui.views.Toolbar
import com.daniyal.cryptomania.viewmodel.CryptoListViewModel

class CryptoListFragment : BaseFragment<CryptoListFragmentBinding, CryptoListViewModel>() {


    override fun setTitleBar(toolbar: Toolbar?) {
        TODO("Not yet implemented")
    }

    override fun getFragmentView() = R.layout.crypto_list_fragment

    override fun getViewModel() = CryptoListViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}