package com.adyabukihari.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adyabukihari.myapplication.R
import com.adyabukihari.myapplication.data.callback.GetNewsCallback
import com.adyabukihari.myapplication.data.callback.ViewHelper
import com.adyabukihari.myapplication.data.model.ArticleResponseModel
import com.adyabukihari.myapplication.ui.adapter.NewsAdapter
import com.adyabukihari.myapplication.utils.Const
import kotlinx.android.synthetic.main.activity_news_list.*


class NewsListActivity : AppCompatActivity(), ViewHelper {

    private val apiKey = Const.API_KEY
    private var category: String? = null
    private var country: String? = null
    private val mData = DataImpl(this@NewsListActivity)
    private lateinit var itemList: MutableList<ArticleResponseModel>
    private lateinit var adapter: NewsAdapter
    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        category = intent.getStringExtra(Const.KEY_CATEGORY)
        country = "id"
        getNewsData()
    }

    private fun getNewsData(){
        // Showing Progress Bar When Data Not Fully Loaded
        showProgress()
        mData.getNews(country = country, category = category, apiKey = apiKey, callback = object:
            GetNewsCallback {

            override fun onSuccess(data: List<ArticleResponseModel>) {
                // Hiding Progress Bar When Data Is Fully Loaded
                hideProgress()

                // Make Mutable List Of Data ( Live Data ) and Setup RecyclerView
                itemList = mutableListOf()
                itemList.clear()
                itemList.addAll(data)
                val layoutManager = LinearLayoutManager(this@NewsListActivity)

                adapter = NewsAdapter(this@NewsListActivity,category = category!!,list = itemList
                ) { article: ArticleResponseModel -> onItemClicked(article) }

                recyclerNews.setHasFixedSize(true)
                adapter.notifyDataSetChanged()
                recyclerNews.layoutManager = layoutManager
                recyclerNews.adapter = adapter
                filterNews()
                adapter.notifyDataSetChanged()
            }

            override fun onError(message: String) {
                Log.d("FailedRetrieveNews", message)
            }

        })
    }

    // Init Filtering News //

    fun filterNews(){
        SearchBox.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun onItemClicked(article: ArticleResponseModel){
        // Send User To InAppBrowser With URL in Articles
        val intent = Intent(this, BrowserActivity::class.java)
        intent.putExtra("url", article.url)
        startActivity(intent)
    }


    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }
}
