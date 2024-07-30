package com.example.bayrakquizuygulamasi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bayrakquizuygulamasi.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.baslaButton.setOnClickListener {
            startActivity(Intent(this,QuizActivity::class.java))
        }
        veritabaniKopyala()

    }

    fun veritabaniKopyala(){
        val dch = DatabaseCopyHelper(this)

        try {

            // veri tabanını oluştur
           dch.createDataBase()

        }catch (e: Exception){
            e.printStackTrace()
        }

        try {

            // veri tabanını aç
            dch.openDataBase()

        }catch (e: Exception){
            e.printStackTrace()
        }


    }
}