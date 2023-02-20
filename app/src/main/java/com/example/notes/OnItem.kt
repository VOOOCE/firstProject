package com.example.notes

import com.example.notes.DataBase.NotesData

interface OnClickItem {
    fun onClick(note:NotesData)
}