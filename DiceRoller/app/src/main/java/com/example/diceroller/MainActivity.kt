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
        rollButton.setOnClickListener {
            if (diceSides == 2) flipCoin(resultTextView) else rollDice(
                resultTextView
            )
        }

        val countButton: Button = findViewById(R.id.count_button)
        countButton.setOnClickListener { countUp(resultTextView) }

        update(resultTextView, true)
        val increaseButton: Button = findViewById(R.id.increase_button)
        increaseButton.setOnClickListener { changeDice(resultTextView, true) }

        val decreaseButton: Button = findViewById(R.id.decrease_button)
        decreaseButton.setOnClickListener { changeDice(resultTextView, false) }
    }

    private fun rollDice(resultTextView: TextView) {
//      Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
        val randomInt: Int = (1..diceSides).random()
        resultTextView.text = randomInt.toString()
    }

    private fun flipCoin(resultTextView: TextView) {
//      Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
        val randomInt: Int = (1..diceSides).random()
        if (randomInt == 1) resultTextView.text =
            getString(R.string.heads_text) else resultTextView.text = getString(R.string.tails_text)
    }

    private fun countUp(resultTextView: TextView) {
        var resultNum: Int = resultTextView.text.toString().toInt()
        resultNum += 1
        if (resultNum == (diceSides + 1)) resultNum = 1 else resultNum %= (diceSides + 1)
        resultTextView.text = resultNum.toString()
    }

    private fun changeDice(resultTextView: TextView, increase: Boolean) {
        if (increase) this.diceSides += 1 else if (diceSides > 2) this.diceSides -= 1
        update(resultTextView, increase)
    }

    private fun update(resultTextView: TextView, increase: Boolean) {
        val headerTextView: TextView = findViewById(R.id.header_text_view)
        val rollButton: Button = findViewById(R.id.roll_button)
        val countButton: Button = findViewById(R.id.count_button)

        var headerNewText: String = getString(R.string.header_roll_text, diceSides)
        if (diceSides == 2 && !increase) {
            headerNewText = getString(R.string.header_flip_text)
            changeToCoin(rollButton, countButton)
            flipCoin(resultTextView)
        } else if (diceSides == 3 && increase) {
            changeToDice(rollButton, countButton)
            rollDice(resultTextView)
        } else rollDice(resultTextView)
        headerTextView.text = headerNewText
    }

    private fun changeToCoin(rollButton: Button, countButton: Button) {
        rollButton.text = getString(R.string.flip_button_text)
        countButton.isEnabled = false
        countButton.isClickable = false
        countButton.alpha = 0F
    }

    private fun changeToDice(rollButton: Button, countButton: Button) {
        rollButton.text = getString(R.string.roll_button_text)
        countButton.isEnabled = true
        countButton.isClickable = true
        countButton.alpha = 1F
    }
}