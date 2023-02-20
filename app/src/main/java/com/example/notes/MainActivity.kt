package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_display_notes.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var displayList = mutableListOf<String>()
    lateinit var countryList : MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ksdsmdmdsdmsdmsdm
            addFragment()

    }

    fun addFragment(){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, DisplayNotes())
            transaction.commit()
        }



}
