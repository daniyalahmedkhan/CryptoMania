package com.daniyal.cryptomania.ui

import android.os.Bundle
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import com.daniyal.cryptomania.R
import com.daniyal.cryptomania.ui.fragments.BaseFragment
import com.daniyal.cryptomania.ui.fragments.CryptoListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {

    protected var baseFragment: BaseFragment? = null
    val KEY_FRAG_FIRST = "firstFrag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        val cryptoListFragment = CryptoListFragment();
        addDockableFragment(cryptoListFragment)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * Add Dockable Fragment
     *
     * @param frag
     */
    fun addDockableFragment(frag: BaseFragment) {
        val animation1 = AlphaAnimation(0.2f, 1.0f)
        animation1.duration = 1000
        animation1.startOffset = 5000
        animation1.fillAfter = true
        val transaction = supportFragmentManager.beginTransaction()
        baseFragment = frag
        transaction.replace(R.id.layout_fragment, frag, frag::class.java.simpleName)
        transaction.addToBackStack(if (supportFragmentManager.backStackEntryCount == 0) KEY_FRAG_FIRST else null)
            .commit()
    }

    /**
     * Pop Fragment from backStack
     */
    fun popFragment() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}