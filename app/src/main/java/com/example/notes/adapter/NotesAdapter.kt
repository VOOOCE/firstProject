package com.example.notes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.*
import com.example.notes.DataBase.NotesData
import kotlinx.android.synthetic.main.fragment_add_note.view.*
import kotlinx.android.synthetic.main.item_rec.view.*
import kotlin.contracts.contract
import kotlin.coroutines.coroutineContext
import kotlin.math.acos

class NotesAdapter(val list: ArrayList<NotesData>, val onClick: OnClickItem):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rec,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int){

        holder.title.cardTitle.text = list[position].title
        holder.note.cardNote.text = list[position].note
        holder.date.cardDate.text = list[position].date
        holder.itemView.setOnClickListener{
            val note = list[position]
            onClick.onClick(note)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NotesViewHolder(view : View) :RecyclerView.ViewHolder(view){
        val title = itemView.cardTitle
        val note = itemView.cardNote
        val date = itemView.cardDate
    }
}