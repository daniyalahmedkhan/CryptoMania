package com.daniyal.cryptomania.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniyal.cryptomania.R
import com.daniyal.cryptomania.data.model.CryptoRates
import com.daniyal.cryptomania.utilities.GeneralHelper
import java.util.*
import kotlin.collections.ArrayList


class CryptoRateListAdapter(
    val cryptoRatesList: List<CryptoRates>, val context: Context,
    val clickListener: (CryptoRates, Int) -> Unit
) :
    RecyclerView.Adapter<CryptoRateListAdapter.ViewHolder>(), Filterable {

    var itemListFilter: List<CryptoRates> = ArrayList<CryptoRates>()

    init {
        itemListFilter = cryptoRatesList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.curreny_rate_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(itemListFilter[position])
    }

    override fun getItemCount(): Int {
        return itemListFilter.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(cryptoRatesList: CryptoRates) {
            val tv_currency_name = itemView.findViewById(R.id.tv_currency_name) as TextView
            val tv_currency_rates = itemView.findViewById(R.id.tv_currency_rates) as TextView

            tv_currency_name.text = cryptoRatesList.symbol
            tv_currency_rates.text = cryptoRatesList.price
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemListFilter = cryptoRatesList
                } else {
                    val resultList = ArrayList<CryptoRates>()
                    for (row in cryptoRatesList.indices) {
                        if (cryptoRatesList.get(row).symbol.toLowerCase(Locale.ROOT)!!
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add((cryptoRatesList[row]))
                        }
                    }
                    itemListFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemListFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemListFilter = results?.values as ArrayList<CryptoRates>
                notifyDataSetChanged()
            }

        }
    }

}