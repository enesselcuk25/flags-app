package com.example.bayrak_uygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        translate()
        veritabaniKopala()

    }
    private fun translate(){
        //geçiş activity
        object :CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val intent = Intent(this@MainActivity,quizActivity::class.java)
                startActivity(intent)
            }
        }.start()
    }

   private fun veritabaniKopala()
    {

        //activity açıldığında verileri getirme
        val copyHelper = DatabaseCopyHelper(this)

        try {
                copyHelper.createDataBase()
            copyHelper.openDataBase()
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}