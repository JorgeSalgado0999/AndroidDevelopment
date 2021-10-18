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
    lateinit var prefs: SharedPreferences
    

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

    fun loadPrefs(view:View?){
        prefs = getSharedPreferences(FILE, MODE_PRIVATE)
        Toast.makeText(this, "PREFS LOADED", Toast.LENGTH_SHORT).show()
    }

    fun printPrefs(view:View?){
        Toast.makeText(this,
                        "VALUE OF FIELD: ${prefs.getString("someKey", "NO VALUE SET")}",
                        Toast.LENGTH_SHORT).show()
    }

    fun savePrefs(view:View?){
        // first retrive an editor
        val editor = prefs.edit()
        editor.putString("someKey", name.text.toString())
        editor.commit()

        Toast.makeText(this, "PREFS SAVED", Toast.LENGTH_SHORT).show()
    }

    fun deleteFiledPrefs(view:View?){
        val editor = prefs.edit()
        editor.remove("someKey")
        editor.commit()

        Toast.makeText(this, "VALUE REMOVED FROM PREFS", Toast.LENGTH_SHORT).show()
    }

    fun DeleteEverythingPrefs(view:View?){
        val editor = prefs.edit()
        editor.clear()
        editor.commit()

        Toast.makeText(this, "EVERYTHING HAS BEEN DELETED", Toast.LENGTH_SHORT).show()
    }


    companion object{
        private const val FILE = "MyPrefs"
    }

}