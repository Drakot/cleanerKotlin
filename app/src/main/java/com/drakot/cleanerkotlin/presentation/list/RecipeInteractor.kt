package com.drakot.cleanerkotlin.presentation.list

import com.drakot.cleanerkotlin.data.DataManager
import com.drakot.cleanerkotlin.data.model.Recipe
import com.drakot.cleanerkotlin.domain.BaseInteractor

class RecipeInteractor : BaseInteractor<Unit, List<Recipe>>() {

    override fun execute() {
        super.execute()
        DataManager.getRecipes({
            callSuccess(it)
        }, {
            callError(it)
        })
    }
}