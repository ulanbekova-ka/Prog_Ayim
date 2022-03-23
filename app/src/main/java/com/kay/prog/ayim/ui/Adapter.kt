package com.kay.prog.ayim.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kay.prog.ayim.R
import com.kay.prog.ayim.database.CharacterEntity

class Adapter(
    private val click: (id: Long) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var list: List<CharacterEntity> = listOf()

    fun setData(list: List<CharacterEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(itemView, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        itemView: View, private val click: (id: Long) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: CharacterEntity) {
            val img = itemView.findViewById<AppCompatImageView>(R.id.img)
            val name = itemView.findViewById<AppCompatTextView>(R.id.name)
            val status = itemView.findViewById<AppCompatTextView>(R.id.status)
            val species = itemView.findViewById<AppCompatTextView>(R.id.species)
            val location = itemView.findViewById<AppCompatTextView>(R.id.location)

            Glide.with(itemView.context).load(character.image).into(img)
            name.text = character.name
            status.text = character.status
            species.text = character.species
            location.text = character.location

            itemView.setOnClickListener {
                click.invoke(character.id)
            }
        }
    }
}