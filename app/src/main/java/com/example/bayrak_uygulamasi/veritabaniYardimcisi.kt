package com.example.bayrak_uygulamasi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class veritabaniYardimcisi(context:Context) : SQLiteOpenHelper(context,"bayrakquiz.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

            db?.execSQL("CREATE TABLE  IF NOT EXISTS bayraklar (bayrak_id INTEGER PRIMARY KEY AUTOINCREMENT,bayrak_adi TEXT, bayrakresim TEXT)")


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS bayraklar")  //veritabanını silip tekara oluşturacağız
        onCreate(db)

    }
}