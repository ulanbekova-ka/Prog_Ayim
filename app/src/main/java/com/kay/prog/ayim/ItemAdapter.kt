package com.kay.prog.ayim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kay.prog.ayim.database.Employee

class ItemAdapter(
    private val click: (id: Long) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var list = listOf<Employee>()

    fun setData(list: List<Employee>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ItemViewHolder(itemView, click)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val e = list[position]
        holder.bind(e)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(
        itemView: View,
        private val click: (id: Long) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(e: Employee) {
            val id = itemView.findViewById<AppCompatTextView>(R.id.item_id)
            val name = itemView.findViewById<AppCompatTextView>(R.id.item_name)
            val company = itemView.findViewById<AppCompatTextView>(R.id.item_company)
            val salary = itemView.findViewById<AppCompatTextView>(R.id.item_salary)

            id.text = e.id.toString()
            name.text = e.name
            company.text = e.company
            salary.text = e.salary.toString()

            itemView.setOnClickListener {
                click.invoke(e.id!!)
            }
        }
    }
}