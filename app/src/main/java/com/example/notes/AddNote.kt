package com.example.notes

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.notes.DataBase.DataBaseH
import kotlinx.android.synthetic.main.fragment_add_note.view.*
import java.util.*


class AddNote : Fragment() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_note, container, false)

        val db = context?.let { DataBaseH(it) }
        root.save.setOnClickListener {

            if (root.title.text.isEmpty() || root.note.text.isEmpty()){

                Toast.makeText(context,"Enter all fields plz",Toast.LENGTH_LONG).show()
            }else{
                val date = SimpleDateFormat("dd/M/yyyy hh:mm:sss")
                val currentdate = date.format(Date())
                var noteTitle = root.title.text.toString()
                var note = root.note.text.toString()
                if (db!!.insertNote(noteTitle,note,currentdate.toString())){
                    Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show()
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,DisplayNotes()).addToBackStack(null).commit()

                }

            }
        }
        root.back.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,DisplayNotes()).addToBackStack(null).commit()

        }


        return root


    }



}
