package com.example.tp4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class ListActivity : AppCompatActivity() {
    val Matiere=arrayOf("Android development","Distributed system","Advanced algorithms","Computer network","IoT")
    val Coefficient= arrayListOf("4","5","2","3","1.5")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var m=findViewById<EditText>(R.id.editText3)
        var c=findViewById<EditText>(R.id.editText4)
        val l=findViewById<ListView>(R.id.list)
        val adapt=ArrayAdapter(this,android.R.layout.simple_list_item_1,Matiere)
        l.adapter=adapt
        l.setOnItemClickListener{parent,view,position,id ->
            m.setText(Matiere[position])
            c.setText(Coefficient[position])}



    }
}
