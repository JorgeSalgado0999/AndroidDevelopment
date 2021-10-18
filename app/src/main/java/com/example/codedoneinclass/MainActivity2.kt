package com.example.codedoneinclass

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity2 : AppCompatActivity() {

    lateinit var db:DBHelper
    lateinit var id:TextView
    lateinit var name:EditText
    lateinit var age:EditText

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //con intent.get... obtenemos la información que se envío
        Toast.makeText(this, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show()


        //database init
        db = DBHelper(this)
        //database vars
        id = findViewById(R.id.storage_id)
        name = findViewById(R.id.storage_name)
        age = findViewById(R.id.storage_age)
    }

    public fun goBack(v:View?){
        val intent = Intent()
        intent.putExtra("result", "todo salio bien")
        intent.putExtra("result2", "salida 2")

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun saveDB(view:View?){
        db.save(name.text.toString(), age.text.toString().toInt())
        Toast.makeText(this, "SAVE ON DB", Toast.LENGTH_SHORT).show()
    }

    fun deleteDB(view:View?){
        val rowsAffected = db.delete(name.text.toString())
        Toast.makeText(this, "ROWS AFFECTED: $rowsAffected", Toast.LENGTH_SHORT).show()

    }

    fun findDB(view:View?){
        val ageFromDB = db.find(name.text.toString())
        age.setText("$ageFromDB")
    }

}