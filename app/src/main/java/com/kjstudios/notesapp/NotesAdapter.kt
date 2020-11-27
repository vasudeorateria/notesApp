package com.kjstudios.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, private val listener: NoteClicked) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =
            NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewHolder.delete.setOnClickListener {
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.text.text = currentNote.text
    }

    fun updateList (newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.text)
        val delete = itemView.findViewById<ImageButton>(R.id.delete)
    }
}

interface NoteClicked {
    fun onItemClick(note: Note)
}