package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nigdroid.coffeeshop.Adapter.CategoryAdapter
import com.nigdroid.coffeeshop.Adapter.PopularAdapter
import com.nigdroid.coffeeshop.Domain.CategoryModel
import com.nigdroid.coffeeshop.ViewModel.MainViewModel
import com.nigdroid.coffeeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initBanner()
        initCatagory()
        initPopular()

        initBottomMenu()
    }

    private fun initBottomMenu() {

        binding.cartBtn.setOnClickListener {

            startActivity(Intent(this@MainActivity,CartActivity::class.java))
        }
    }


    private fun initBanner() {
        binding.progressBar.visibility= View.VISIBLE
        viewModel.loadBanner().observeForever {

            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.imgBanner)
            binding.progressBar.visibility= View.GONE

        }
        viewModel.loadBanner()
    }

//    private fun initCatagory() {
//        binding.progressBar2.visibility= View.VISIBLE
//        viewModel.loadCatagory().observeForever {
//
//            binding.recyclerView1.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
//            binding.recyclerView1.adapter=CategoryAdapter(it)
//            binding.progressBar2.visibility= View.GONE
//
//        }
//        viewModel.loadCatagory()
//    }
    // Inside MainActivity.kt

    private fun initCatagory() {
        binding.progressBar2.visibility = View.VISIBLE
        viewModel.loadCatagory().observeForever {
            binding.recyclerView1.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            // Pass the click listener implementation
            binding.recyclerView1.adapter = CategoryAdapter(it, object : CategoryAdapter.OnCategoryClickListener {
                override fun onCategoryClick(category: CategoryModel, position: Int) {
                    // Handle the category click here
                    // For example, start a new activity and pass the category data
                    // val intent = Intent(this@MainActivity, CategoryItemsActivity::class.java)
                    // intent.putExtra("category_name", category.name) // Assuming 'category' has a 'name' property
                    // startActivity(intent)
                }
            })
            binding.progressBar2.visibility = View.GONE
        }
        viewModel.loadCatagory()
    }

    private fun initPopular() {
        binding.progressBar3.visibility= View.VISIBLE
        viewModel.loadPopular().observeForever {

            binding.recyclerView2.layoutManager=GridLayoutManager(this,2)
            binding.recyclerView2.adapter=PopularAdapter(it)
            binding.progressBar3.visibility= View.GONE


        }

        viewModel.loadPopular()


    }



}