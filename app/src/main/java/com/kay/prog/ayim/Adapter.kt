package com.kay.prog.ayim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.api.Episode

class Adapter(
    private val click: (id: Long) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var list: List<Episode> = listOf()

    fun setData(list: List<Episode>) {
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

        fun bind(episode: Episode) {
            val title = itemView.findViewById<AppCompatTextView>(R.id.title)
            title.text = episode.title

            itemView.setOnClickListener {
                click.invoke(episode.episode_id)
            }
        }
    }
}