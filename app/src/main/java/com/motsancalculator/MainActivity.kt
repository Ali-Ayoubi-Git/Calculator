package com.motsancalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.motsancalculator.ui.theme.MotsanCalculatorTheme

class MainActivity : ComponentActivity() {
    private val input = mutableListOf<String>()
    private var resultTexBox: TextView? = null
    private var infixExpression: Expression? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        resultTexBox = findViewById(R.id.ResultTextBox)

    }

    fun onClick(button: View) {

        val ButtonText = (button as Button).text.toString()
        when (ButtonText) {
            "=" -> {
                infixExpression = Expression((input))
                resultTexBox?.text = infixExpression!!.evaluateExpression().toString()
                input.clear()
                input.add(resultTexBox?.text.toString())
            }

            "CL" -> {
                input.clear()
                resultTexBox?.text = " "


            }

            "C" -> {
                resultTexBox?.text = "${resultTexBox?.text}".dropLast(1)
                if (resultTexBox?.text?.isNotEmpty()!! && resultTexBox?.text?.last() == ' ') resultTexBox?.text =
                    "${resultTexBox?.text}".dropLast(1)

                if (input.last().length == 1) input.removeAt(input.lastIndex)
                else input[input.lastIndex] = input.last().dropLast(1)
            }

            else -> {
                if (Character.isDigit(ButtonText[0]) || ButtonText[0] == '.') {
                    if (input.isNotEmpty() && Character.isDigit(input.last()[0])) {
                        input[input.lastIndex] = input.last() + ButtonText
                        resultTexBox?.text = "${resultTexBox?.text}${button.text}"

                    } else {
                        input.add(ButtonText)
                        resultTexBox?.text = "${resultTexBox?.text} ${button.text}"
                    }
                } else {
                    input.add(ButtonText)
                    resultTexBox?.text = "${resultTexBox?.text} ${button.text}"

                }

            }
        }
    }

}

