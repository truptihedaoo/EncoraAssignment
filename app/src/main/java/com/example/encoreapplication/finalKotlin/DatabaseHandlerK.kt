package com.example.encoreapplication.finalKotlin



import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList

class DatabaseHandlerK(context: Context)//3rd argument to be passed is CursorFactory instance
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val allData: List<ExampleItems>
        get() {
            val contactList = ArrayList<ExampleItems>()
            val selectQuery = "SELECT  * FROM $TABLE_SONGS"

            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val contact = ExampleItems()
                    contact._id = Integer.parseInt(cursor.getString(0))
                    contact.setmCreator(cursor.getString(1))
                    contact.setmImageUrl(cursor.getString(2))
                    contact.setmLikes(cursor.getString(3))
                    contactList.add(contact)
                } while (cursor.moveToNext())
            }
            return contactList
        }

    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_SONGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_IMAGE_URL + " TEXT," + KEY_URL + " TEXT" + ")")
        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SONGS")

        // Create tables again
        onCreate(db)
    }

    fun addSongs(exampleItem: ExampleItems) {
        val db = this.writableDatabase
        //
        val values = ContentValues()
        values.put(KEY_NAME, exampleItem.getmCreator()) // Contact Name
        values.put(KEY_IMAGE_URL, exampleItem.getmImageUrl()) // Contact Phone
        values.put(KEY_URL, exampleItem.getmLikes()) // Contact Phone

        // Inserting Row
        db.insert(TABLE_SONGS, null, values)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
    }

    internal fun getContact(id: Int): ExampleItems {
        val db = this.readableDatabase

        val cursor = db.query(
            TABLE_SONGS, arrayOf(KEY_ID, KEY_NAME, KEY_IMAGE_URL, KEY_URL), "$KEY_ID=?",
            arrayOf(id.toString()), null, null, null, null
        )
        cursor?.moveToFirst()

        return ExampleItems(
            Integer.parseInt(cursor!!.getString(0)),
            cursor.getString(1), cursor.getString(2), cursor.getString(3)
        )
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "contactsManager"
        private val TABLE_SONGS = "songs"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_IMAGE_URL = "imageurl"
        private val KEY_URL = "url"
    }

}