package com.drakot.cleanerkotlin.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drakot.cleanerkotlin.R
import com.drakot.cleanerkotlin.data.model.Recipe
import com.drakot.cleanerkotlin.presentation.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        baseActivity?.enableToolbarBackButton(true)
    }

    private fun initViews() {
        val recipe = arguments?.getSerializable("recipe") as? Recipe
        tvRecipeName.text = recipe?.title

        if (recipe?.thumbnail?.isNotEmpty() == true) {
            Picasso.get()
                .load(recipe.thumbnail)
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivRecipe)

        }
    }

}