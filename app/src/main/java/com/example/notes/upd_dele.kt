package com.example.notes

import android.app.AlertDialog
import android.content.DialogInterface
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.notes.DataBase.DataBaseH
import kotlinx.android.synthetic.main.fragment_add_note.view.*
import kotlinx.android.synthetic.main.fragment_upd_dele.*
import kotlinx.android.synthetic.main.fragment_upd_dele.view.*
import kotlinx.android.synthetic.main.item_rec.view.*
import java.util.*

class upd_dele : Fragment() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root =  inflater.inflate(R.layout.fragment_upd_dele, container, false)
        var id = requireArguments().getInt("id")
        var title = requireArguments().getString("title")
        var note = requireArguments().getString("note")
        root.titlev.setText("$title")
        root.notev.setText("$note")
        val db = context?.let { DataBaseH(it) }
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Note")
        builder.setMessage("Are u sure ?")
        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            if (db!!.deleteNote(id)) {

                Toast.makeText(context, "deleted done", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DisplayNotes()).addToBackStack(null).commit()

            } else {
                Toast.makeText(context, "deleted field", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("No") { _: DialogInterface, _: Int -> }
        root.dele.setOnClickListener {
            builder.show()


        }
        root.saveup.setOnClickListener {
            var titlev = root.titlev.text.toString()
            var notev = root.notev.text.toString()
            val date = SimpleDateFormat("dd/M/yyyy hh:mm:sss")
            val currentdate = date.format(Date())
            if (title == titlev && note == notev){
                Toast.makeText(context, "can't update same values", Toast.LENGTH_SHORT).show()
            }else{
                if (db!!.updateNote(id,titlev,notev,currentdate)){
                    Toast.makeText(context, "updated done", Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,DisplayNotes()).addToBackStack(null).commit()
                }else{
                    Toast.makeText(context, "updated field", Toast.LENGTH_SHORT).show()
                }
            }
        }
        root.backup.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,DisplayNotes()).addToBackStack(null).commit()

        }

        return root
    }

}