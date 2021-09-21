package com.daniyal.cryptomania.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniyal.cryptomania.ui.BaseActivity
import com.daniyal.cryptomania.ui.views.Toolbar
import com.facebook.shimmer.ShimmerFrameLayout

abstract class BaseFragment<T : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected lateinit var binding: T
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getFragmentView(),
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(getViewModel())
        return binding.root

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

    abstract fun getFragmentView(): Int

    abstract fun getViewModel(): Class<VM>

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