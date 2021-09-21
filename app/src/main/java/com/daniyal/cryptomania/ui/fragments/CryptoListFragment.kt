package com.daniyal.cryptomania.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniyal.cryptomania.R
import com.daniyal.cryptomania.adapters.CryptoRateListAdapter
import com.daniyal.cryptomania.data.model.CryptoRates
import com.daniyal.cryptomania.data.repo.remote.ResponseEvent
import com.daniyal.cryptomania.databinding.CryptoListFragmentBinding
import com.daniyal.cryptomania.utilities.UIHelper
import com.daniyal.cryptomania.viewmodel.CryptoListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CryptoListFragment : BaseFragment() {

    private lateinit var cryptoListFragmentBinding: CryptoListFragmentBinding
    private lateinit var cryptoRateListAdapter: CryptoRateListAdapter


    private val cryptoListViewModel: CryptoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cryptoListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.crypto_list_fragment, container, false
        )

        return cryptoListFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        cryptoListFragmentBinding.rvPaymentoption.layoutManager =
            LinearLayoutManager(requireActivity())
        cryptoListViewModel.getCryptoRates()


        cryptoListViewModel.itemState.observe(requireActivity(), Observer {
            when (it) {

                is ResponseEvent.Loading -> {
                    shimmerEffect(cryptoListFragmentBinding.shimmerViewContainer, true)
                }
                is ResponseEvent.Failure -> {
                    shimmerEffect(cryptoListFragmentBinding.shimmerViewContainer, false)
                    UIHelper.showDialog(requireActivity(), it.error)

                }
                is ResponseEvent.Success<List<CryptoRates>> -> {
                    shimmerEffect(cryptoListFragmentBinding.shimmerViewContainer, false)
                    setUpList(it.data!!)
                }
            }
        })


        /*
      * Search View  to sort list items
      * */
        cryptoListFragmentBinding.itemSearch.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                cryptoRateListAdapter.filter.filter(p0)
                return false
            }

        })
    }

    private fun setUpList(list: List<CryptoRates>) {
        cryptoRateListAdapter =
            CryptoRateListAdapter(list, requireActivity()) { item: CryptoRates, position: Int -> }
        cryptoListFragmentBinding.rvPaymentoption.adapter = cryptoRateListAdapter
    }

}