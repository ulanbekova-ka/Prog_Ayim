package com.kay.prog.ayim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SimpleAdapter : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
    private var list = listOf<String>()

    fun setData(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = list[position]
        holder.bind(text)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageAddress1 = "https://animesher.com/orig/1/127/1275/12755/animesher.com_girl-circle-icons-one-punch-man-1275581.jpg"
//        private val imageAddress2 = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fanimesher.com%2Fentry%2Fanime-girl-circle-icons-glasses-1154205%2F&psig=AOvVaw0xbiXNCedGXxOLLjikGT5H&ust=1639658927772000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCOiFgdfr5fQCFQAAAAAdAAAAABAb"
//        private val imageAddress1 = R.drawable.ic_launcher_foreground
        private val imageAddress2 = R.drawable.ic_launcher_background
        // TODO: Все работает нормально с картинками из drawable, а вот с инета не грузит

        fun bind(text: String) {
            val txt = itemView.findViewById<AppCompatTextView>(R.id.item_txt)
            txt.text = text

            val image = itemView.findViewById<AppCompatImageView>(R.id.item_img)
            if (adapterPosition % 2 == 0) {
                Glide.with(itemView.context).load(imageAddress1).into(image)
            } else {
                Glide.with(itemView.context).load(imageAddress2).into(image)
            }
        }
    }
}