package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.nigdroid.coffeeshop.R

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


      var  edtTxt : EditText = findViewById(R.id.edtTxt)

        var button3 : Button = findViewById(R.id.button3)

        button3.setOnClickListener {

            val nigu = edtTxt.getText().toString()

            val sP = getSharedPreferences("MyPref", MODE_PRIVATE)
            val ed = sP.edit()
            ed.putString("name", nigu)
            ed.apply()

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))

        }





    }
}