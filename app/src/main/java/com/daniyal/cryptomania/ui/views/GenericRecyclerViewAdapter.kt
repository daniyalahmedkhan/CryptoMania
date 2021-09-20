package com.daniyal.cryptomania.ui.views

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class  GenericRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var list: List<T?>? = null
    var context: Context? = null
    var mItems: MutableList<T>? = null
    // Allows to remember the last item shown on screen
    var lastPosition = -1

    fun GenericRecyclerViewAdapter(context: Context, items: MutableList<T>?) {
        this.context = context
        this.mItems = items
        this.list = items
    }

//    public abstract int getLayoutView();

    //    public abstract int getLayoutView();
    abstract fun setViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder

    abstract fun onBindData(holder: RecyclerView.ViewHolder?, `val`: T?, position: Int)

    fun setItemView(layout: Int, parent: ViewGroup): View? {
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return setViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindData(holder, mItems!![position], holder.adapterPosition)
    }

    /**
     * Here is the key method to apply the animation
     */
    open fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    fun addItems(savedCardItemz: MutableList<T>?) {
        mItems = savedCardItemz
        this.notifyDataSetChanged()
    }

    fun getItem(position: Int): T? {
        return mItems!![position]
    }


    fun add(item: T?, position: Int) {
        if (item == null) {
            return
        }
        mItems!!.add(position, item)
        notifyItemInserted(position)
    }

    fun add(items: List<T>?, position: Int) {
        if (items == null || items.isEmpty()) {
            return
        }
        mItems!!.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }

    fun add(item: T) {
        val position = mItems!!.size
        mItems!!.add(position, item)
//        notifyItemInserted(position);
    }

    fun addItemTop(item: T) {
        val position = mItems!!.size
        mItems!!.add(0, item)
        notifyItemInserted(0)
    }

    fun add(items: List<T>) {
        if (items.isEmpty()) {
            return
        }
        val position = if (mItems!!.isEmpty()) 0 else mItems!!.size
        mItems!!.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }


    fun getItems(): List<T>? {
        return mItems
    }

    override fun getItemCount(): Int {
        return mItems!!.size
    }

    /**
     * Requires equals() and hashcode() to be implemented in T class.
     */
    fun remove(item: T) {
        val position = mItems!!.indexOf(item)
        if (position == -1) {
            return
        }
        mItems!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun remove(position: Int) {
        mItems!!.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mItems!!.size)
    }

    fun remove(position: Int, itemCount: Int) {
        for (i in position until itemCount) {
            mItems!!.removeAt(i)
        }
        notifyItemRangeRemoved(position, itemCount)
    }

    fun update(position: Int, model: T) {
        mItems!![position] = model
        notifyItemChanged(position)
    }

    fun clear() {
        mItems!!.clear()
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                list = results.values as List<T?>
                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence): FilterResults {
                var filteredResults: List<T>? = null
                if (constraint.length == 0) {
                    filteredResults = mItems
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase())
                }
                val results = FilterResults()
                results.values = filteredResults
                return results
            }
        }
    }

    fun getFilteredResults(constraint: String): List<T>? {
        val results: MutableList<T> = ArrayList<T>()
        for (item in mItems?.iterator()!!) {
            if (item.toString().toLowerCase().contains(constraint.toLowerCase())) {
                results.add(item)
            }
        }
        return results
    }


}