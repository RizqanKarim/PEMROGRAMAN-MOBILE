package com.example.laprak1_xml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivDice1 = findViewById<ImageView>(R.id.ivDice1)
        val ivDice2 = findViewById<ImageView>(R.id.ivDice2)
        val btnRoll = findViewById<Button>(R.id.btnRoll)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)

        btnRoll.setOnClickListener {
            val random1 = (1..6).random()
            val random2 = (1..6).random()

            val drawableResource1 = when (random1) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            ivDice1.setImageResource(drawableResource1)

            val drawableResource2 = when (random2) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            ivDice2.setImageResource(drawableResource2)

            if (random1 == random2) {
                tvMessage.text = "Selamat, anda dapat dadu double!"
            } else {
                tvMessage.text = "Anda belum beruntung!"
            }

            tvMessage.visibility = android.view.View.VISIBLE
        }
    }
}