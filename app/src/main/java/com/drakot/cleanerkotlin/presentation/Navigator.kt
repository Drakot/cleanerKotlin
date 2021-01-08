package com.drakot.cleanerkotlin.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.drakot.cleanerkotlin.R
import com.drakot.cleanerkotlin.data.model.Recipe
import com.drakot.cleanerkotlin.presentation.base.BaseActivity
import com.drakot.cleanerkotlin.presentation.base.BaseFragment
import com.drakot.cleanerkotlin.presentation.detail.RecipeDetailFragment

class Navigator(var activity: BaseActivity?) {

    private var fragmentManager: FragmentManager? = activity?.supportFragmentManager
    private var addBackStack = true
    fun navigate(fragmentClass: BaseFragment) {
        val trans = fragmentManager?.beginTransaction()
        trans?.replace(R.id.container, fragmentClass, fragmentClass.tag)
        if (addBackStack)
            trans?.addToBackStack(null)
        trans?.commit()
    }

    fun addToBackStack(addToBackStack: Boolean): Navigator {
        this.addBackStack = addToBackStack
        return this
    }

    fun navigateToRecipeDetail(
        recipe: Recipe
    ) {
        val fragment = RecipeDetailFragment()
        fragment.arguments = Bundle().apply {
            this.putSerializable("recipe", recipe)
        }
        navigate(fragment)
    }
}