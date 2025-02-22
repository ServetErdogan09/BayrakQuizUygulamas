package com.example.bayrakquizuygulamasi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(val context : Context) : SQLiteOpenHelper(context,"bayrakquiz.sqlite",null,1) {
    // tabloları oluşturduğumuz method
    override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL("CREATE TABLE IF NOT EXISTS \"bayraklar\" (\n" +
            "\t`bayrak_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`bayrak_ad`\tTEXT,\n" +
            "\t`bayrak_resim`\tTEXT\n" +
            ")")
    }

    // veri tabanı da sorun çıkarsa yapılacak işlemler methodu
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db?.execSQL("DROP TABLE IF EXISTS bayraklar")
    onCreate(db)
    }
}