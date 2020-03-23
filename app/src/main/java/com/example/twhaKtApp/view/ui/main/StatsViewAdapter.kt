package com.example.twhaKtApp.view.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twhaKtApp.R
import com.example.twhaKtApp.model.TwhaAnswerModel

class StatsViewAdapter(
    private val list: List<TwhaAnswerModel>,
    private val listener: ListListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ListListener {
        fun onClickItem(tappedView: View, twhaAnswerModel: TwhaAnswerModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.stats_item_model, parent, false)
        return StatsViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.stats_item_model).text = list[position].date
        holder.itemView.setOnClickListener {
            listener.onClickItem(it, list[position])
        }
    }

    override fun getItemCount(): Int = list.size
}