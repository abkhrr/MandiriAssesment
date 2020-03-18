package com.adyabukihari.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.adyabukihari.myapplication.R
import com.adyabukihari.myapplication.ui.adapter.CategoryAdapter
import com.adyabukihari.myapplication.ui.model.CategoryModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Adding New List Of Category
    private val categoryList = listOf(
        CategoryModel("Business"),
        CategoryModel("Entertainment"),
        CategoryModel("Health"),
        CategoryModel("Science"),
        CategoryModel("Sports"),
        CategoryModel("Technology")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showCategory()
    }

    private fun showCategory(){
        categoryRecycler.setHasFixedSize(true)
        categoryRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CategoryAdapter(list = categoryList)
        }
    }


}
