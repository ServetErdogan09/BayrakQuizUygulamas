package com.example.bayrakquizuygulamasi

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bayrakquizuygulamasi.databinding.ActivityQuizBinding
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {

    private lateinit var sorular: ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler: ArrayList<Bayraklar>
    private lateinit var bayraklardao: Bayraklardao
    private lateinit var dogruSoru : Bayraklar
    private lateinit var veritabaniYardimcisi: VeritabaniYardimcisi
    private lateinit var tumSecenekler: HashSet<Bayraklar>

    private var  soruSayac = 0
    private var  dogruSayac = 0
    private var  yanlisSayac = 0


    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bayraklardao = Bayraklardao()
        veritabaniYardimcisi = VeritabaniYardimcisi(this)




        sorular = bayraklardao.rastgele5BayrakGetir(veritabaniYardimcisi)


        sorulariYukle()
        tiklananButton()

    }

    fun sorulariYukle() {
        binding.soruText.text = "${soruSayac + 1}.Soru"

        dogruSoru = sorular[soruSayac]

        val resourceName = dogruSoru.bayrak_ad.lowercase().replace(" ", "")
        Log.e("ResourceName", resourceName)

        val resourceId = resources.getIdentifier(resourceName, "drawable", packageName)
        Log.e("ResourceID", resourceId.toString())

        yanlisSecenekler = bayraklardao.rastgele3YnalisSecenekGetir(veritabaniYardimcisi,dogruSoru.bayrak_id)

        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler[0])
        tumSecenekler.add(yanlisSecenekler[1])
        tumSecenekler.add(yanlisSecenekler[2])


        binding.buttonA.text = tumSecenekler.elementAt(0).bayrak_ad
        binding.buttonB.text = tumSecenekler.elementAt(1).bayrak_ad
        binding.buttonC.text = tumSecenekler.elementAt(2).bayrak_ad
        binding.buttonD.text = tumSecenekler.elementAt(3).bayrak_ad

        if (resourceId != 0) {
            val drawable = ContextCompat.getDrawable(this, resourceId)
            binding.bayrakImage.setImageDrawable(drawable)
        } else {
            Log.e("Resim Hatası", "Resim bulunamadı: $resourceName")
        }


    }


    fun soruSayacKontrol(){
        soruSayac++
        if (soruSayac != 5) {
            sorulariYukle()
        }else{

            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("dogruSayisi",dogruSayac)
            intent.putExtra("yanlisSayisi",yanlisSayac)
            startActivity(intent)
            finish()
        }
    }


    fun dogruKontrol(button: Button){

        val text = button.text.toString()
        val dogruCevap = dogruSoru.bayrak_ad

        if (dogruCevap == text){
            dogruSayac++
            binding.dogruText.text = "Doğru:${dogruSayac}"
        }else{
            yanlisSayac++
            binding.yanlisText.text = "Yanlış:${yanlisSayac}"
        }
    }

    fun tiklananButton(){

        binding.buttonA.setOnClickListener {
            dogruKontrol(binding.buttonA)
            soruSayacKontrol()
        }

        binding.buttonB.setOnClickListener {
            dogruKontrol(binding.buttonB)
            soruSayacKontrol()
        }

        binding.buttonC.setOnClickListener {
            dogruKontrol(binding.buttonC)
            soruSayacKontrol()
        }

        binding.buttonD.setOnClickListener {
            dogruKontrol(binding.buttonD)
            soruSayacKontrol()
        }
    }

}