package com.kjstudios.notesapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  , NoteClicked {

    lateinit var viewmodel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = NotesAdapter(this ,this)
        noteRecyclerView.adapter = adapter

        viewmodel = ViewModelProvider(this ,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewmodel.allNotes.observe(this , { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

        submit.setOnClickListener {
            val noteText = input.text.toString()
            if(noteText.isNotEmpty()){
                viewmodel.insertNote(Note(noteText))
                Toast.makeText(this, "$noteText Added", Toast.LENGTH_LONG).show()

            }
        }

    }

    override fun onItemClick(note: Note) {
        viewmodel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_LONG).show()
    }

}