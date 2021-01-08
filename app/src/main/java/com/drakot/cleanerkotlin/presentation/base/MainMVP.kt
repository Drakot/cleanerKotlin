package com.drakot.cleanerkotlin.presentation.base

import com.drakot.cleanerkotlin.data.remote.ErrorResponse

class MainMVP {

    interface View {
        fun showProgress(show: Boolean)
        fun showMessage(message: String)
        fun showError(error: String)

    }

    interface Presenter {

    }

    interface Interactor {
        fun execute()

        interface CallbackResult<in T> {
            fun onSuccess(result: T)
            fun onError(error: ErrorResponse)
            fun onFinish() {}
        }
    }

}