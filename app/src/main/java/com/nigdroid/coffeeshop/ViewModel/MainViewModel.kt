package com.nigdroid.coffeeshop.ViewModel

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.nigdroid.coffeeshop.Domain.BannerModel
//import com.nigdroid.coffeeshop.Domain.CategoryModel
//import com.nigdroid.coffeeshop.Domain.ItemsModel
//import com.nigdroid.coffeeshop.Repository.MainRepository
//
//class MainViewModel:ViewModel() {
//
//    private val repository=MainRepository()
//
//    fun loadBanner():LiveData<MutableList<BannerModel>>{
//
//        return repository.loadBanner()
//
//    }
//    fun loadCatagory():LiveData<MutableList<CategoryModel>>{
//
//        return repository.loadCatagory()
//
//    }
//
//    fun loadPopular():LiveData<MutableList<ItemsModel>>{
//        return repository.loadPopular()
//
//    }
//    fun loadItems(categoryId:String):LiveData<MutableList<ItemsModel>>{
//
//        return repository.loadItemCategory(categoryId)
//
//    }
//
//}
// Inside MainViewModel.kt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // Requires lifecycle-viewmodel-ktx dependency
import com.nigdroid.coffeeshop.Domain.BannerModel
import com.nigdroid.coffeeshop.Domain.CategoryModel
import com.nigdroid.coffeeshop.Domain.ItemsModel
import com.nigdroid.coffeeshop.Repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _banner = MutableLiveData<List<BannerModel>>()
    val banner: LiveData<List<BannerModel>> = _banner

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    private val _popularItems = MutableLiveData<List<ItemsModel>>()
    val popularItems: LiveData<List<ItemsModel>> = _popularItems

    fun loadBanner() {
        viewModelScope.launch {
            // Switch to the IO dispatcher for network or disk operations
            val result = withContext(Dispatchers.IO) {
                // Perform your data fetching here (e.g., network call, database query)
                // Replace with your actual data fetching logic
                MainRepository.loadBanner() // Assuming you have a repository
            }
            // Update the LiveData on the main thread
            _banner.value = result
        }
    }

    fun loadCatagory() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                // Perform your data fetching here
                yourRepository.fetchCategories()
            }
            _categories.value = result
        }
    }

    fun loadPopular() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                // Perform your data fetching here
                yourRepository.fetchPopularItems()
            }
            _popularItems.value = result
        }
    }
}