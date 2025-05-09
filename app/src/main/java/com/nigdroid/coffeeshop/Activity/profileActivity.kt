package com.nigdroid.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nigdroid.coffeeshop.Activity.LoginActivity
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.databinding.ActivityProfileBinding

class profileActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)


        val sP = getSharedPreferences("MyPref", MODE_PRIVATE)

        val name = sP.getString("name", "")
        val phone = sP.getString("phone", "")
        val email = sP.getString("email", "")
        val address = sP.getString("address", "")

        val words=name?.split(" ")
        val firstWord=words?.get(0)

      var username_text : TextView = findViewById(R.id.username_text)
        username_text.text = "Hello ! "+firstWord

        var email_text : TextView = findViewById(R.id.email_text)
        email_text.text = email

        var contact_info_text : TextView = findViewById(R.id.contact_info_text)
        contact_info_text.text ="Phone no. : "+ phone

        var contact_info_text2 : TextView = findViewById(R.id.contact_info_text2)
        contact_info_text2.text ="Email : "+ email

        var about_me_text : TextView = findViewById(R.id.about_me_text)
        about_me_text.text ="You Name is : "+ name

        var achievements_placeholder : TextView = findViewById(R.id.achievements_placeholder)
        achievements_placeholder.text ="Address : "+ address


        var logout_button : TextView = findViewById(R.id.logout_button)

        logout_button.setOnClickListener {

            val sP = getSharedPreferences("MyPref", MODE_PRIVATE)
            val editor = sP.edit()
            editor.clear()
            editor.apply()

            startActivity(Intent(this@profileActivity, LoginActivity::class.java))

            Toast.makeText(this@profileActivity,"Logout Successfully..",Toast.LENGTH_SHORT).show()

        }

        var img_back : LinearLayout = findViewById(R.id.img_back)

        img_back.setOnClickListener {
            finish()
        }

    }
}