package com.example.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.DataBase.DataBaseH
import com.example.notes.DataBase.NotesData
import com.example.notes.adapter.NotesAdapter
import kotlinx.android.synthetic.main.fragment_display_notes.view.*
import java.util.*
import kotlin.collections.ArrayList

class DisplayNotes : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_display_notes, container, false)
        val db = context?.let { DataBaseH(it) }
        val notee = db!!.getAllNotes()
            root.recycler.setHasFixedSize(true)
            root.recycler.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

            val notesAdapter = NotesAdapter(notee,object:OnClickItem{
                override fun onClick(note: NotesData) {
                    val b = Bundle()
                    b.putInt("id",note.id)
                    b.putString("title",note.title)
                    b.putString("note",note.note)
                    b.putString("date",note.date)
                    var updDele = upd_dele()
                    updDele.arguments = b
                    activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,updDele).addToBackStack(null).commit()

                }
            })
            root.recycler.adapter = notesAdapter


        root.add.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,AddNote()).addToBackStack(null).commit()
        }

        return root
    }




}
