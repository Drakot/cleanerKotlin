package com.drakot.cleanerkotlin.domain

import com.drakot.cleanerkotlin.data.remote.ErrorResponse
import com.drakot.cleanerkotlin.presentation.base.MainMVP
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

abstract class BaseInteractor<O, C>() : MainMVP.Interactor {
    val LOG_TAG = this::class.java.simpleName
    var requestData: O? = null
    var callback: MainMVP.Interactor.CallbackResult<C>? = null
    var timeStart: Long = 0
    var timeTaken: Long = 0

    private  var response: ((item: C) -> Unit)? = null
    private lateinit var errorResponse: (error: ErrorResponse) -> Unit

    constructor(init: BaseInteractor<O, C>.() -> Unit) : this() {
        init()
    }

    fun callSuccess(result: C) {
        measureTimeTaken()
        doAsync {
            uiThread {
                if (callback == null) {
                    response?.invoke(result)
                } else {
                    callback?.onFinish()
                    callback?.onSuccess(result)
                }
            }
        }
    }

    fun callError(err: ErrorResponse) {
        measureTimeTaken()
        doAsync {
            uiThread {
                if (callback == null) {
                    errorResponse(err)
                } else {
                    callback?.onFinish()
                    callback?.onError(err)
                }
            }
        }
    }

    private fun measureTimeTaken() {
        timeTaken = System.currentTimeMillis() - timeStart
        timeStart = 0
    }

    override fun execute() {}

    fun run() {
        timeStart = System.currentTimeMillis()
        doAsync {
            execute()
        }
    }

    fun doRequest(
            data: O? = null,
            call: MainMVP.Interactor.CallbackResult<C>
    ) {
        requestData = data
        callback = call
        run()
    }

    fun doRequest(
            call: MainMVP.Interactor.CallbackResult<C>
    ) {
        callback = call
        run()
    }

    fun requestData(requestData: O): BaseInteractor<O, C> {
        this.requestData = requestData
        return this
    }

    fun doRequest(requestData: O? = null, response: (item: C) -> Unit, errorResponse: (error: ErrorResponse) -> Unit) {
        this.requestData = requestData
        this.response = response
        this.errorResponse = errorResponse
        run()
    }

    fun doRequest(response: (item: C) -> Unit, errorResponse: (error: ErrorResponse) -> Unit) {
        this.requestData = requestData
        this.response = response
        this.errorResponse = errorResponse
        run()
    }

}