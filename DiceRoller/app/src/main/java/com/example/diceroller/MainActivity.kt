package com.example.diceroller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var diceSides: Int = 6


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultTextView: TextView = findViewById(R.id.result_text_view)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice(resultTextView) }

        val countButton: Button = findViewById(R.id.count_button)
        countButton.setOnClickListener { countUp(resultTextView) }

        headerTextUpdate()
        val increaseButton: Button = findViewById(R.id.increase_button)
        increaseButton.setOnClickListener { changeDice(true) }

        val decreaseButton: Button = findViewById(R.id.decrease_button)
        decreaseButton.setOnClickListener { changeDice(false) }
    }

    private fun rollDice(resultTextView: TextView) {
//      Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
        val randomInt: Int = (1..diceSides).random()
        resultTextView.text = randomInt.toString()
    }

    private fun countUp(resultTextView: TextView) {
        var resultNum: Int = resultTextView.text.toString().toInt()
        resultNum += 1
        if (resultNum == (diceSides + 1)) resultNum = 1 else resultNum %= (diceSides + 1)
        resultTextView.text = resultNum.toString()
    }

    private fun changeDice(increase: Boolean) {
        if (increase) this.diceSides += 1 else if (diceSides > 3) this.diceSides -= 1
        headerTextUpdate()
    }

    private fun headerTextUpdate() {
        val headerNewText: String = getString(R.string.header_text, diceSides)
        val headerTextView: TextView = findViewById(R.id.header_text_view)
        headerTextView.text = headerNewText
    }
}