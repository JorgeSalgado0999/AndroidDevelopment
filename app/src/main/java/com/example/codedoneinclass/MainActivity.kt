package com.example.codedoneinclass

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    // Declaramos atributos
    lateinit var textView : TextView // lateinit porque daremos el valor adelante
    lateinit var editText : EditText
    lateinit var button : Button
    lateinit var button2 : Button

    // recive return values
    val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->

        // siempre revisar el resultado
        if(result.resultCode == Activity.RESULT_OK){
            // entra si todo esta bien
            val data : Intent? = result.data
            Toast.makeText(this, data?.getStringExtra("result"), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)

        // Crear un toast
        Toast.makeText(this, textView.getText().toString(), Toast.LENGTH_LONG).show() // El length es la duración

        // Change text of a textView
        textView.setText("Changed from code")

        // Imprimir mesajes, el primer parametro es una etiqueta usada para buscar nuestros prints
        Log.d("TESTLOG", "debug")
        Log.i("TESTLOG", "info")
        Log.w("TESTLOG", "warning")
        Log.e("TESTLOG", "error")
        Log.wtf("TESTLOG", "what a terrible failure")

        // Acciones con click 2 metodos:
        // por un listener
        button.setOnClickListener(){
            Toast.makeText(this, textView.getText().toString(), Toast.LENGTH_LONG).show() // El length es la duración

        }// Cierra Listener

    }// cierra On create

    //por el layout
    public fun clicked (v:View?){
        //launch activity
        val intent = Intent(this, MainActivity2::class.java)

        //enviamos información con putExtra
        intent.putExtra("name", editText.getText().toString())

        //startActivity(intent)
        activityResultLauncher.launch(intent)

    }// Cierra function


}// Cierra MainActivity