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
    private var resultTexBox :TextView?=null
    private var infixExpression:Expression?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        resultTexBox = findViewById(R.id.ResultTextBox)
    }

    fun onClick(button: View) {
        val BottenText = (button as Button).text.toString()
        when(BottenText){
            "="->{
                infixExpression=Expression((input))
                resultTexBox?.text=infixExpression!!.evaluateExpression().toString()
                }
            else -> {
                input.add(BottenText)
                resultTexBox?.text="${resultTexBox?.text}${button.text}"
            }
            }

        }


    }
