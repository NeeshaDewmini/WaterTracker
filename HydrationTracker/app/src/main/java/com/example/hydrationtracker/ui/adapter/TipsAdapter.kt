package com.example.hydrationtracker.ui.adapter  // ✅ Ensure this matches the correct package

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hydrationtracker.R
import com.example.hydrationtracker.model.Tip
import kotlin.random.Random

class TipsAdapter(private val tipsList: List<Tip>, private val context: Context) :
    RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {

    private val colors = listOf(
        R.color.bluelight,
        R.color.dark_blue,

    )

    class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tipText: TextView = itemView.findViewById(R.id.tvTipText)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tip, parent, false)
        return TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.tipText.text = tipsList[position].text

        val randomColor = ContextCompat.getColor(context, colors[Random.nextInt(colors.size)])
        holder.cardView.setCardBackgroundColor(randomColor) // ✅ Fixed getColor issue
    }

    override fun getItemCount(): Int = tipsList.size
}



