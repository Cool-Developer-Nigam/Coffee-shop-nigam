package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nigdroid.coffeeshop.Activity.MainActivity
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.databinding.ActivitySplashBinding

class Splash_Activity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {

startActivity(Intent(this@Splash_Activity, MainActivity::class.java))


        }
    }
}