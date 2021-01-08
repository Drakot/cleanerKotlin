package com.drakot.cleanerkotlin.presentation.base

import androidx.fragment.app.Fragment
import com.drakot.cleanerkotlin.presentation.Navigator


abstract class BaseFragment : Fragment(), MainMVP.View {

    val baseActivity: BaseActivity?
        get() {
            return activity as? BaseActivity
        }

    val navigator: Navigator?
        get() {
            return baseActivity?.navigator
        }

    override fun showProgress(show: Boolean) {
        baseActivity?.showProgress(show)
    }

    override fun showMessage(message: String) {
        baseActivity?.showMessage(message)
    }

    override fun showError(message: String) {
        baseActivity?.showMessage(message)
    }

}

