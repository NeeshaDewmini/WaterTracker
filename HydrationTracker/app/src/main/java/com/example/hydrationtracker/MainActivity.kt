package com.example.hydrationtracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hydrationtracker.ui.TipsEducationActivity
import com.example.hydrationtracker.ui.WaterReminderActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnWaterReminder: Button = findViewById(R.id.btnWaterReminder)
        val btnTips: Button = findViewById(R.id.btnTips)

        btnWaterReminder.setOnClickListener {
            val intent = Intent(this, WaterReminderActivity::class.java)
            startActivity(intent)
        }

        btnTips.setOnClickListener {
            val intent = Intent(this, TipsEducationActivity::class.java)
            startActivity(intent)
        }
    }
}



