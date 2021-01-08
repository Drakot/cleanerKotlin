package com.drakot.cleanerkotlin.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakot.cleanerkotlin.R
import com.drakot.cleanerkotlin.data.model.Recipe
import com.drakot.cleanerkotlin.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class RecipeListFragment : BaseFragment(), RecipeListPresenter.View {

    private lateinit var adapter: RecipeAdapter
    private lateinit var presenter: RecipeListPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recipe_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseActivity?.enableToolbarBackButton(false)
        initList()
        presenter = RecipeListPresenter(this)
        presenter.init()
    }

    private fun initList() {
        rvRecipes.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rvRecipes.setHasFixedSize(false)
        rvRecipes.layoutManager = LinearLayoutManager(activity)
        adapter = RecipeAdapter {
            navigator?.navigateToRecipeDetail(it)
        }
        rvRecipes.adapter = adapter
    }

    override fun setRecipes(it: List<Recipe>) {
        adapter.data = it
        adapter.notifyDataSetChanged()
    }
}