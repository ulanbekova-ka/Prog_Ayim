package com.kay.prog.ayim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var list: List<Item> = listOf()

    fun setData(list: List<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {
            val name = itemView.findViewById<AppCompatTextView>(R.id.name)
            val ownerLogin = itemView.findViewById<AppCompatTextView>(R.id.owner)
            val htmlUrl = itemView.findViewById<AppCompatTextView>(R.id.htmlUrl)

            name.text = item.name
            ownerLogin.text = item.owner?.login ?: ""
            htmlUrl.text = item.html_url
        }
    }
}