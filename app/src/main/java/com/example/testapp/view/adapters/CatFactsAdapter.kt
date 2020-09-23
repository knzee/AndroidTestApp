package com.example.testapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.model.data.CatFact
import java.text.SimpleDateFormat

class CatFactsAdapter() : RecyclerView.Adapter<CatFactsAdapter.MainHolder>() {

    private var catFacts = emptyList<CatFact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false))

    override fun getItemCount() = catFacts.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val current = catFacts[position]
        holder.factText.text = current.text

        val datePattern = "dd-MM-yyyy hh:mm"
        val formatter = SimpleDateFormat(datePattern)
        val tempDate = formatter.format(current.createdAt)
        holder.creationDate.text = tempDate
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val factText = itemView.findViewById<TextView>(R.id.factText)
        val creationDate = itemView.findViewById<TextView>(R.id.factDate)
    }

    internal fun setCatFacts(facts: List<CatFact>) {
        this.catFacts = facts
        notifyDataSetChanged()
    }

}