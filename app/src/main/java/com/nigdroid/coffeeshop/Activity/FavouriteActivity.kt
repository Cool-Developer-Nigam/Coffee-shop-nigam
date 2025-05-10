package com.nigdroid.coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigdroid.coffeeshop.Helper.ManagementFavourite // We'll create this
import com.nigdroid.coffeeshop.Adapter.FavouriteAdapter // We'll need to create this
import com.nigdroid.coffeeshop.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var managementFavourite: ManagementFavourite

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementFavourite = ManagementFavourite(this) // Initialize our favourite manager

        initFavouriteList()
        setVariable()
    }

    private fun initFavouriteList() {
        binding.apply {
            if (managementFavourite.getListFavourite().isEmpty()) {
                emptyTxt.visibility = View.VISIBLE
                favouriteListView.visibility = View.GONE
            } else {
                emptyTxt.visibility = View.GONE
                favouriteListView.visibility = View.VISIBLE
                favouriteListView.layoutManager = LinearLayoutManager(
                    this@FavouriteActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                // You'll need to create FavouriteAdapter similar to CartAdapter
                favouriteListView.adapter = FavouriteAdapter(
                    managementFavourite.getListFavourite(),
                    this@FavouriteActivity
                    // If you need to handle removals from the favourite list in the UI,
                    // you might pass a listener here similar to ChangeNumberItemsListener
                )
            }
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {
            finish() // Close this activity and go back
        }
    }
}