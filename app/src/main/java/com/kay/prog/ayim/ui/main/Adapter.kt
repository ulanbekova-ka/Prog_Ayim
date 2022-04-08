package com.kay.prog.ayim.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.databinding.ItemRecyclerBinding

class Adapter(
    private val click: (id: Long) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var list: List<CharacterEntity> = listOf()

    fun setData(list: List<CharacterEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        private val binding : ItemRecyclerBinding,
        private val click: (id: Long) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterEntity) {
            binding.apply {
                Glide.with(itemView.context).load(character.image).into(img)
                name.text = character.name
                status.text = character.status
                species.text = character.species
                location.text = character.location
            }

            itemView.setOnClickListener {
                click.invoke(character.id)
            }
        }
    }
}