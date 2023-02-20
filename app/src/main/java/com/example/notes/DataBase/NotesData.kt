package com.example.notes.DataBase


class NotesData (var id : Int ,var title : String,var note : String , var date : String) {
    companion object {
        const val TABLE_NAME = "NOTES"
        const val COL_ID = "_id"
        const val COL_TITLE = "title"
        const val COL_NOTE = "note"
        const val COL_DATE = "date"
        const val TABLE_CREATE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT , $COL_TITLE TEXT ,$COL_NOTE TEXT,$COL_DATE TEXT)"
    }
}