package com.drakot.cleanerkotlin.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakot.cleanerkotlin.R
import com.drakot.cleanerkotlin.data.model.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter(var data: List<Recipe?> = emptyList(), var onSelected: ((Recipe) -> Unit)) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private var current: Recipe? = null

        fun bind(position: Int) {
            current = data[position]
            current?.let { recipe ->
                view.tvIngredients.text = recipe.ingredients.toString()
                view.tvTitle.text = recipe.title
                view.tvUrl.text = recipe.href
                if (recipe.thumbnail?.isNotEmpty() == true) {
                    Picasso.get()
                        .load(recipe.thumbnail)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(view.ivImage)
                }

                view.setOnClickListener {
                    onSelected(recipe)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size


}