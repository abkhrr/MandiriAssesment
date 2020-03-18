package com.adyabukihari.myapplication.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.adyabukihari.myapplication.R
import com.adyabukihari.myapplication.ui.NewsListActivity
import com.adyabukihari.myapplication.ui.model.CategoryModel
import com.adyabukihari.myapplication.utils.Const
import com.adyabukihari.myapplication.utils.enums.Category
import kotlinx.android.synthetic.main.item_category_list.view.*

// Build For Category RecyclerView

class CategoryAdapter(private val list: List<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_list,parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.categoryTitle.text = list[position].category
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private var mContext: Context = view.context

        var click = view.setOnClickListener{
            when (adapterPosition) {
                0 -> {
                    val intent = Intent(mContext, NewsListActivity::class.java)
                    intent.putExtra(Const.KEY_CATEGORY, Category.BUSINESS.value)
                    startActivity(mContext, intent, Bundle.EMPTY)
                }
                1 -> {
                    val intent = Intent(mContext, NewsListActivity::class.java)
                    intent.putExtra(Const.KEY_CATEGORY, Category.ENTERTAINMENT.value)
                    startActivity(mContext, intent, Bundle.EMPTY)
                }
                2 -> {
                    val intent = Intent(mContext, NewsListActivity::class.java)
                    intent.putExtra(Const.KEY_CATEGORY, Category.HEALTH.value)
                    startActivity(mContext, intent, Bundle.EMPTY)
                }
                3 -> {
                    val intent = Intent(mContext, NewsListActivity::class.java)
                    intent.putExtra(Const.KEY_CATEGORY, Category.SCIENCE.value)
                    ContextCompat.startActivity(mContext, intent, Bundle.EMPTY)
                }
                4 -> {
                    val intent = Intent(mContext, NewsListActivity::class.java)
                    intent.putExtra(Const.KEY_CATEGORY, Category.SPORTS.value)
                    ContextCompat.startActivity(mContext, intent, Bundle.EMPTY)
                }
                5 -> {
                    val intent = Intent(mContext, NewsListActivity::class.java)
                    intent.putExtra(Const.KEY_CATEGORY, Category.TECHNOLOGY.value)
                    ContextCompat.startActivity(mContext, intent, Bundle.EMPTY)
                }
            }
        }
    }

}