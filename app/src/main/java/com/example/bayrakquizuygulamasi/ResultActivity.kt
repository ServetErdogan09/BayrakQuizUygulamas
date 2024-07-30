package com.example.bayrakquizuygulamasi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bayrakquizuygulamasi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tekrarButton.setOnClickListener {
            startActivity(Intent(this,QuizActivity::class.java))
            finish()
        }



        puanHesapla()
    }

    fun puanHesapla(){
        val intent = intent
      val  dogruSayisi =   intent.getIntExtra("dogruSayisi",0)
      val yanlisSayisi =   intent.getIntExtra("yanlisSayisi",0)

        binding.sonucText.text = "${dogruSayisi} DOĞRU  ${yanlisSayisi} YANLIŞ"

        var yuzde = dogruSayisi*20

        binding.yuzdeSonucText.text = "%${yuzde} Başarı"
    }
}