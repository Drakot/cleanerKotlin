package com.drakot.cleanerkotlin.presentation.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.drakot.cleanerkotlin.presentation.Navigator
import com.drakot.cleanerkotlin.setVisible
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity(), MainMVP.View {
    val navigator: Navigator?
        get() {
            return Navigator(this)
        }
    override fun showProgress(show: Boolean) {
        progressContainer?.setVisible(show)
        progress?.setVisible(show)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    fun enableToolbarBackButton(enable: Boolean) {
        val actionBar = supportActionBar
        if (enable) {
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
        } else {
            actionBar?.setDisplayHomeAsUpEnabled(false)
            actionBar?.setDisplayShowHomeEnabled(false)
        }
    }


}