package com.example.hydrationtracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hydrationtracker.R
import com.example.hydrationtracker.model.Tip
import com.example.hydrationtracker.ui.adapter.TipsAdapter  // ✅ Ensure correct import

class TipsEducationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tipsAdapter: TipsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_education)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val tipsList = listOf(
            Tip("Drink water before meals to aid digestion."),
            Tip("Staying hydrated boosts your energy levels."),
            Tip("Always carry a water bottle to stay hydrated."),
            Tip("Drink water as soon as you wake up for better metabolism."),
            Tip("Reduce sugary drinks; replace them with water.")
        )

        tipsAdapter = TipsAdapter(tipsList, this) // ✅ Pass `this` as context
        recyclerView.adapter = tipsAdapter
    }
}



