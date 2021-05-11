package com.example.bayrak_uygulamasi

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.alert_tasarim.*
import kotlinx.android.synthetic.main.alert_tasarim.view.*

class quizActivity : AppCompatActivity() {


    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogrusoru:Bayraklar
    private lateinit var tumsecenekler :HashSet<Bayraklar> //HashSet kullanımı soruları karıştırıyor
    private lateinit var vt:veritabaniYardimcisi


    private var soruSayac = 0
    private var dogrSayac = 0
    private var yanlisSayac = 0

    var dialog: Dialog? =null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        tanimla()
        dialog = Dialog(this)
    }
     fun tanimla(){
        btn_a.setOnClickListener {
            startActivity(Intent(this,quizActivity::class.java))
            finish()
        }

        //rastgele soru getiryor
        vt = veritabaniYardimcisi(this)
        sorular = Bayraklardao().rasgelesoru(vt)
        soruYukle()
        button()

    }

     fun button(){
        btn_a.setOnClickListener {
            dogruKontrol(it as Button)
            sarusayac()
        }
        btn_b.setOnClickListener {
            dogruKontrol(it as Button)
            sarusayac()
        }
        btn_c.setOnClickListener {
            dogruKontrol(it as Button)
            sarusayac()
        }
        btn_d.setOnClickListener {
            dogruKontrol(it as Button)
            sarusayac()
        }
    }


     fun soruYukle(){
        texview_SoruSayisi.text = "${soruSayac+1}.Soru"

        dogrusoru = sorular.get(soruSayac) //1. soru,2.soru..geliyor

        imageView_bayrak.setImageResource(resources.getIdentifier(dogrusoru.bayrak_resim,"drawable",packageName))
        yanlisSecenekler = Bayraklardao().rastgeleUcSoruGetir(vt,dogrusoru.bayrak_id)

        tumsecenekler = HashSet()
        tumsecenekler.add(dogrusoru)
        tumsecenekler.add(yanlisSecenekler.get(0))
        tumsecenekler.add(yanlisSecenekler.get(1))
        tumsecenekler.add(yanlisSecenekler.get(2))

        btn_a.text = tumsecenekler.elementAt(0).bayrak_ad
        btn_b.text = tumsecenekler.elementAt(1).bayrak_ad
        btn_c.text = tumsecenekler.elementAt(2).bayrak_ad
        btn_d.text = tumsecenekler.elementAt(3).bayrak_ad

    }




     fun sarusayac() {
        soruSayac++
        if (soruSayac != 5) {
            soruYukle()
        } else {
           
                 dialog!!.setContentView(R.layout.alert_tasarim)
            var textview_Sonuc = dialog!!.findViewById<TextView>(R.id.textview_Alert_Sonuc)
            var textview_Oran = dialog!!.findViewById<TextView>(R.id.textview_Alert_Oran)
            var btn_evet = dialog!!.btn_Alert_Evet
            var  btn_hayir = dialog!!.btn_Alert_Hayır


            textview_Sonuc.text = "${dogrSayac} Doğru ${5 - dogrSayac} Yanliş"
            textview_Oran.text = "%${dogrSayac*100/5} Başarı"


            btn_evet.setOnClickListener {
                val intent = Intent(this,quizActivity::class.java)
                startActivity(intent)
            }
            btn_hayir.setOnClickListener {

                dialog!!.cancel()
               val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
                startActivity(intent)

            }

            dialog!!.show()


        }
    }


     fun dogruKontrol(button: Button){
        val btnYazi = button.text.toString()
        val dogrucevap = dogrusoru.bayrak_ad

        if(btnYazi.equals(dogrucevap)){
            dogrSayac++

        }
        else{
            yanlisSayac++
        }

        textview_dogruSayisi.text = "${dogrSayac}.Doğru"
        texview_yanlisSayisi.text = "${yanlisSayac}.Yanliş"

    }
}


