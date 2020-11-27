package com.kjstudios.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotesTable")
class Note(
    @ColumnInfo(name = "NoteText") val text: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}