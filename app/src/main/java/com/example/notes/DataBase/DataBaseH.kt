package com.example.notes.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseH(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {



    private  var db : SQLiteDatabase
    init {
        db = this.writableDatabase
    }
    override fun onCreate(p0: SQLiteDatabase?){
        p0!!.execSQL(NotesData.TABLE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${NotesData.TABLE_NAME}")
        onCreate(p0)
    }
    fun  insertNote(title :String ,note : String , date : String ) : Boolean{
        val cv = ContentValues()
        cv.put(NotesData.COL_TITLE,title)
        cv.put(NotesData.COL_NOTE,note)
        cv.put(NotesData.COL_DATE,date)
        return db.insert(NotesData.TABLE_NAME,null,cv) >0
    }
    fun getAllNotes() : ArrayList<NotesData>{
        var notes  = ArrayList<NotesData>()
        val c = db.rawQuery("SELECT * FROM ${NotesData.TABLE_NAME} Order by ${NotesData.COL_DATE} DESC",null)
        c.moveToFirst()
        while (!c.isAfterLast){
            val n = NotesData(c.getString(0).toInt(),c.getString(1),c.getString(2),c.getString(3))
            notes.add(n)
            c.moveToNext()
        }
        c.close()
        return notes
    }
    fun deleteNote(id:Int) : Boolean{
        return  db.delete(NotesData.TABLE_NAME,"${NotesData.COL_ID}= $id ",null ) >0
    }
    fun updateNote(oldId : Int ,title :String ,note : String , date : String) : Boolean{
        val cv = ContentValues()
        cv.put(NotesData.COL_TITLE,title)
        cv.put(NotesData.COL_NOTE,note)
        cv.put(NotesData.COL_DATE,date)
        return  db.update(NotesData.TABLE_NAME,cv,"${NotesData.COL_ID} = $oldId", null) >0
    }

    companion object {
        const val DATABASE_NAME = "note"
        const val DATABASE_VERSION = 1
    }
}