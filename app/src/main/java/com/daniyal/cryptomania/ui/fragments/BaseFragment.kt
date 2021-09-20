package com.daniyal.cryptomania.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniyal.cryptomania.ui.BaseActivity
import com.daniyal.cryptomania.ui.views.Toolbar
import com.facebook.shimmer.ShimmerFrameLayout

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        // setTitleBar(getBaseActivity().getToolbar())
    }

    /* Toolbar */
    protected abstract fun setTitleBar(toolbar: Toolbar?)

    /* Get BaseActivity */
    protected fun getBaseActivity(): BaseActivity? {
        return activity as BaseActivity?
    }

    /*
     * RecyclerView manager method */
    protected fun bindLinearLayoutManagers(
        myRecyclerView: RecyclerView,
        isVerticalOrientation: Boolean
    ) {
        myRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(
            getBaseActivity(),
            if (isVerticalOrientation == true) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL,
            false
        )
        myRecyclerView.layoutManager = layoutManager
        myRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    /*
     * To show shimmer effect on loading
     * */
    protected fun shimmerEffect(shimmer: ShimmerFrameLayout, isVisible: Boolean) {
        if (isVisible) {
            shimmer.visibility = View.VISIBLE
            shimmer.startShimmer()
        } else {
            shimmer.visibility = View.GONE
            shimmer.stopShimmer()
        }
    }
}