package com.example.codedoneinclass

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//SQLite es una base de datos local relacional
// ESta clase se encargara de manejar la base de datos

// debe heredar de SQLIte Helper
// en esta línea tenemos nuestro constructor que llama a super de la clase padre y le pasa el context que le pasamos a dbhelper
public class DBHelper(context : Context?) : SQLiteOpenHelper(context, "DB_FILE", null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        // cuando se crea el archivo
        val query = "CREATE TABLE $TABLE(" +
                "$COLUMN_ID INTEGER PRIMARY KEY, "+
                "$COLUMN_NAME TEXT, "+
                "$COLUMN_AGE INTEGER)"

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, previousVersion: Int, CurrentVersion: Int) {
        // cuando cambia la versión
        //do any mantenance to update your db

        //? -> WILDCARD (comodín)
        val query = "DROP TABLE IF EXISTS  ?"
        val args = arrayOf(TABLE)

        db?.execSQL(query, args)
        onCreate(db)
    }

    fun save(name:String, age:Int){
        //add new row
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_AGE, age)

        writableDatabase.insert(TABLE, null, values)
    }

    fun delete(name:String):Int{
        val clause = "$COLUMN_NAME = ?"
        val args = arrayOf(name)
        return writableDatabase.delete(TABLE, clause, args)
    }

    fun find(name:String):Int{
        //search a record using name
        val clause = "$COLUMN_NAME = ?"
        val args = arrayOf(name)

        //cursor is the iterator that tells us in wich record from result we are set
        //null means return everything
        val cursor = readableDatabase.query(TABLE, null, clause, args, null, null, null)

        var result = -1

        //first verify if set its not empty
        if(cursor.moveToFirst())
            //values: 0->id 1->txt 2->age
            result = cursor.getInt(2)


        //transverse the set
        while (cursor.moveToNext()){
            //do something
        }

        return result
    }


    //Companion object
    //contenedor de variables
    companion object {
        private const val DB_FILE = "kittens.db"
        private const val TABLE = "kittens"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_AGE = "age"
    }

}