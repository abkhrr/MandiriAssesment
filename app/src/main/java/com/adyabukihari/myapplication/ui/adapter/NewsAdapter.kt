package com.adyabukihari.myapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adyabukihari.myapplication.R
import com.adyabukihari.myapplication.data.model.ArticleResponseModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_category_list.view.*
import kotlinx.android.synthetic.main.item_news_list.view.*

class NewsAdapter(private val context: Context,private val category: String, private val list: List<ArticleResponseModel>, private val clickListener: (ArticleResponseModel) -> Unit)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_list, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ArticleResponseModel = list[position]
        holder.bind(item, context, clickListener, category)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        // Bind View To OnBindViewHolder
        fun bind(articles: ArticleResponseModel, context: Context, clickListener: (ArticleResponseModel) -> Unit, category: String) {
            Glide.with(context)
                .load(articles.urlToImage)
                .apply(RequestOptions().placeholder(android.R.drawable.ic_menu_gallery))
                .into(view.imageNews)
            view.articleTitle.text = articles.title
            view.sourceFrom.text = articles.source!!.name
            view.categoryNews.text = category

            // Set Item OnClick
            view.newsLayout.setOnClickListener {
                clickListener(articles)
            }
        }

    }
}
