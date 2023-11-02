package com.testing.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.testing.calculator.databinding.ActivityMainBinding
import com.testing.calculator.MainActivity
import com.testing.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator = Calculator()
    private var currentInput = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set onClick listeners for number buttons
        binding.button0.setOnClickListener { appendNumber("0") }
        binding.button1.setOnClickListener { appendNumber("1") }
        binding.button2.setOnClickListener { appendNumber("2") }
        binding.button3.setOnClickListener { appendNumber("3") }
        binding.button4.setOnClickListener { appendNumber("4") }
        binding.button5.setOnClickListener { appendNumber("5") }
        binding.button6.setOnClickListener { appendNumber("6") }
        binding.button7.setOnClickListener { appendNumber("7") }
        binding.button8.setOnClickListener { appendNumber("8") }
        binding.button9.setOnClickListener { appendNumber("9") }

        // Set onClick listeners for operation buttons
        binding.buttonAdd.setOnClickListener { performOperation("+") }
        binding.buttonSubtract.setOnClickListener { performOperation("-") }
        binding.buttonMultiply.setOnClickListener { performOperation("*") }
        binding.buttonDivide.setOnClickListener { performOperation("/") }
        binding.buttonEquals.setOnClickListener { calculateResult() }
        binding.buttonClear.setOnClickListener { clearInput() }
    }

    private fun appendNumber(s: String) {
        currentInput.append(s)
        updateInputText()
    }

    private fun performOperation(operator: String) {
        currentInput.append(" $operator ")
        updateInputText()
    }

    private fun calculateResult() {
        try {
            val inputParts = currentInput.toString().split(" ")
            if (inputParts.size == 3) {
                val operand1 = inputParts[0].toDouble()
                val operator = inputParts[1]
                val operand2 = inputParts[2].toDouble()
                val result = when (operator) {
                    "+" -> calculator.add(operand1, operand2)
                    "-" -> calculator.subtract(operand1, operand2)
                    "*" -> calculator.multiply(operand1, operand2)
                    "/" -> calculator.divide(operand1, operand2)
                    else -> throw IllegalArgumentException("Invalid Operator")
                }
                currentInput.setLength(0)
                currentInput.append(result)
                updateInputText()
                    } else {
                        throw IllegalArgumentException("Invalid Input Format")
                    }
                } catch (e: Exception) {
                    currentInput.setLength(0)
                    currentInput.append("Error")
                    updateInputText()
                }
            }

    private fun clearInput() {
        currentInput.setLength(0)
        updateInputText()
    }

    private fun updateInputText() {
        binding.inputTextView.text = currentInput.toString()
    }
}