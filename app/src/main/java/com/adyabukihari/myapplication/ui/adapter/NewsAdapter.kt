package com.adyabukihari.myapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.adyabukihari.myapplication.R
import com.adyabukihari.myapplication.data.model.ArticleResponseModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_news_list.view.*

class NewsAdapter(
    private val context: Context,
    private val category: String,
    private var list: MutableList<ArticleResponseModel>,
    private val clickListener: (ArticleResponseModel) -> Unit)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>(), Filterable {

    // Init Filtered Method
    private var newsFiltered = mutableListOf<ArticleResponseModel>()

    init {
        newsFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_list, parent, false))
    }

    override fun getItemCount(): Int = newsFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ArticleResponseModel = newsFiltered[position]
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

    // Performing Filter For News Source Or Title //

    override fun getFilter(): Filter {
        return object : Filter(){
            @SuppressLint("DefaultLocale")
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                newsFiltered = if (charSearch.isEmpty()) {
                    list
                } else {
                    val resultList = mutableListOf<ArticleResponseModel>()
                    for (row in list) {
                        if (row.title!!.toLowerCase().contains(charSearch.toLowerCase())
                            || row.source!!.name!!.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = newsFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                newsFiltered = results?.values as MutableList<ArticleResponseModel>
                notifyDataSetChanged()
            }

        }
    }
}
