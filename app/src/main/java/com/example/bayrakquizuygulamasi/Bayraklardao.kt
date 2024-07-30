package com.example.bayrakquizuygulamasi

class Bayraklardao {

    fun rastgele5BayrakGetir(vt : VeritabaniYardimcisi ,) : ArrayList<Bayraklar>{

        val bayraklarList = ArrayList<Bayraklar>()

        // veri tabanı üzerinde işlem yapma iznini aldık
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5 ",null)

        while (cursor.moveToNext()){

            val bayrak = Bayraklar(
                cursor.getInt(cursor.getColumnIndexOrThrow("bayrak_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("bayrak_ad")),
                cursor.getString(cursor.getColumnIndexOrThrow("bayrak_resim"))
            )
            bayraklarList.add(bayrak)
        }

        cursor.close()
        db.close()

        return bayraklarList
    }


    fun rastgele3YnalisSecenekGetir(vt : VeritabaniYardimcisi , bayrak_id : Int) : ArrayList<Bayraklar>{

        val bayraklarList = ArrayList<Bayraklar>()

        // veri tabanı üzerinde işlem yapma iznini aldık
        val db = vt.writableDatabase

        // 3 tane cevabın olmadığı bayrak verisi getirecek
        val cursor = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != $bayrak_id ORDER BY RANDOM() LIMIT 3 ",null)

        while (cursor.moveToNext()){

            val bayrak = Bayraklar(
                cursor.getInt(cursor.getColumnIndexOrThrow("bayrak_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("bayrak_ad")),
                cursor.getString(cursor.getColumnIndexOrThrow("bayrak_resim"))
            )
            bayraklarList.add(bayrak)
        }

        cursor.close()
        db.close()

        return bayraklarList
    }
}