package com.example.bayrak_uygulamasi

class Bayraklardao {
    fun rasgelesoru(vt:veritabaniYardimcisi) : ArrayList<Bayraklar>{
        val bayraklarListe = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)

        while (c.moveToNext()){
            val bayrak = Bayraklar(c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim")))
            bayraklarListe.add(bayrak)
        }
        return bayraklarListe
    }

    //rastegele 3 soru geliyor ve bu sorular yanliş seceneklerden gelecek
     fun rastgeleUcSoruGetir(vt:veritabaniYardimcisi,bayrak_id:Int) : ArrayList<Bayraklar>{
        val bayraklarListe = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != $bayrak_id ORDER BY RANDOM() LIMIT 3",null)

        while (c.moveToNext()){
            val bayrak = Bayraklar(c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim")))
            bayraklarListe.add(bayrak)
        }
        return bayraklarListe
    }
}