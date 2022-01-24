package com.example.diceroller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultTextView: TextView = findViewById(R.id.result_text_view)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice(resultTextView) }

        val countButton: Button = findViewById(R.id.count_button)
        countButton.setOnClickListener { countUp(resultTextView) }
    }

    private fun rollDice(resultTextView: TextView) {
//      Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
        val randomInt: Int = (1..6).random()
        resultTextView.text = randomInt.toString()
    }

    private fun countUp(resultTextView: TextView) {
        var resultNum: Int = resultTextView.text.toString().toInt()
        resultNum += 1
        if (resultNum == 7) resultNum = 1 else resultNum %= 7
        resultTextView.text = resultNum.toString()
    }
}