package com.motsancalculator

import java.lang.Math.pow
import java.util.*


class Expression(var infixExpression: MutableList<String>) {
    private var postFix: String = ""
    private fun infixToPostfix() {
        var result = ""
        val stack = Stack<String>()
        for (elament in infixExpression) {
            if (elament.all { it.isDigit() } || elament.any { it == '.' }) {
                result += "$elament "
            } else if (elament == "(") {
                stack.push(elament)
            } else if (elament == ")") {
                while (stack.peek() != "(" && stack.isNotEmpty()) {
                    result += "${stack.pop()}"
                }
                if (stack.isNotEmpty())
                    stack.pop()
            } else {
                while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(elament)) {
                    result += "${stack.pop()} "
                }
                stack.push(elament)

            }
        }
        while (stack.isNotEmpty()) {
            result += "${stack.pop()}"
        }
        postFix = result

    }

    private fun precedence(operator: String): Int {
        return when (operator) {
            "^" -> 4
            "X", "÷" -> 3
            "+", "-" -> 2
            "%" -> 1
            else -> -1
        }
    }


    fun evaluateExpression(): Number {
        infixToPostfix()
        val stack = Stack<Double>()

        var i = 0
        while (i < postFix.length) {
            if (postFix[i] == ' ') {
                i++
                continue
            } else if (Character.isDigit(postFix[i])) {
                var number = ""
                while (Character.isDigit(postFix[i]) || postFix[i] == '.') {
                    number += postFix[i]
                    i++
                }
                stack.push(number.toDouble())
            } else {
                val x = stack.pop()
                val y = stack.pop()
                when (postFix[i]) {
                    'X' -> stack.push(x * y)
                    '÷' -> stack.push(y / x)
                    '+' -> stack.push(y + x)
                    '-' -> stack.push(y - x)
                    '^' -> stack.push(pow(y, x))
                    '%' -> stack.push((y/100) * x)

                }
            }
            i++
        }
        return if (stack.peek() / stack.peek().toInt() == 1.0) stack.peek()
            .toInt() else stack.peek()
    }
}