package com.example.codedoneinclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.view.View
import android.content.SharedPreferences
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //con intent.get... obtenemos la información que se envío
        Toast.makeText(this, intent.getStringExtra("name"), Toast.LENGTH_SHORT).show()
    }

    public fun goBack(v:View?){
        val intent = Intent()
        intent.putExtra("result", "todo salio bien")
        intent.putExtra("result2", "salida 2")

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}