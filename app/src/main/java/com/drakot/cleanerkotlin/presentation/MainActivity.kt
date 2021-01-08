package com.drakot.cleanerkotlin.presentation

import android.os.Bundle
import android.view.MenuItem
import com.drakot.cleanerkotlin.R
import com.drakot.cleanerkotlin.presentation.base.BaseActivity
import com.drakot.cleanerkotlin.presentation.list.RecipeListFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator?.addToBackStack(false)?.navigate(RecipeListFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}