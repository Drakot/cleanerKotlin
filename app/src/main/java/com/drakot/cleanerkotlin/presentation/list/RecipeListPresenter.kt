package com.drakot.cleanerkotlin.presentation.list

import com.drakot.cleanerkotlin.data.model.Recipe
import com.drakot.cleanerkotlin.presentation.base.MainMVP

class RecipeListPresenter(val view: View) : MainMVP.Presenter {
    private val interactor: RecipeInteractor by lazy { RecipeInteractor() }

    fun init() {
        view.showProgress(true)
        interactor.doRequest({
            view.showProgress(false)
            view.setRecipes(it)
        }, {
            //TODO pending parse enum errors into strings
            view.showProgress(false)
            view.showError(it.name)
        })
    }

    interface View : MainMVP.View {
        fun setRecipes(it: List<Recipe>)

    }
}