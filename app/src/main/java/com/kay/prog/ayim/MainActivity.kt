package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private var expression = StringBuilder("")
    private var lastInp = ' '
    private var isBracketOpened = false
    private lateinit var inpTxt: AppCompatTextView
    private lateinit var resultTxt: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inpTxt = findViewById(R.id.input_txt)

        val oneButton = findViewById<AppCompatButton>(R.id.one_button)
        oneButton.setOnClickListener(::onClick)
        val twoButton = findViewById<AppCompatButton>(R.id.two_button)
        twoButton.setOnClickListener(::onClick)
        val threeButton = findViewById<AppCompatButton>(R.id.three_button)
        threeButton.setOnClickListener(::onClick)
        val fourButton = findViewById<AppCompatButton>(R.id.four_button)
        fourButton.setOnClickListener(::onClick)
        val fiveButton = findViewById<AppCompatButton>(R.id.five_button)
        fiveButton.setOnClickListener(::onClick)
        val sixButton = findViewById<AppCompatButton>(R.id.six_button)
        sixButton.setOnClickListener(::onClick)
        val sevenButton = findViewById<AppCompatButton>(R.id.seven_button)
        sevenButton.setOnClickListener(::onClick)
        val eightButton = findViewById<AppCompatButton>(R.id.eight_button)
        eightButton.setOnClickListener(::onClick)
        val nineButton = findViewById<AppCompatButton>(R.id.nine_button)
        nineButton.setOnClickListener(::onClick)
        val zeroButton = findViewById<AppCompatButton>(R.id.zero_button)
        zeroButton.setOnClickListener(::onClick)
        val addButton = findViewById<AppCompatButton>(R.id.add_button)
        addButton.setOnClickListener(::onClick)
        val subtractButton = findViewById<AppCompatButton>(R.id.subtract_button)
        subtractButton.setOnClickListener(::onClick)
        val multiplyButton = findViewById<AppCompatButton>(R.id.multiply_button)
        multiplyButton.setOnClickListener(::onClick)
        val divideButton = findViewById<AppCompatButton>(R.id.divide_button)
        divideButton.setOnClickListener(::onClick)
        val dotButton = findViewById<AppCompatButton>(R.id.dot_button)
        dotButton.setOnClickListener(::onClick)
        val clearButton = findViewById<AppCompatButton>(R.id.all_clear_button)
        clearButton.setOnClickListener(::onClick)
        val openButton = findViewById<AppCompatButton>(R.id.open_bracket_button)
        openButton.setOnClickListener(::onClick)
        val closeButton = findViewById<AppCompatButton>(R.id.close_bracket_button)
        closeButton.setOnClickListener(::onClick)
        val backButton = findViewById<AppCompatButton>(R.id.go_back_button)
        backButton.setOnClickListener(::onClick)

        resultTxt = findViewById(R.id.solution_txt)
        val equalButton = findViewById<AppCompatButton>(R.id.equal_button)
        equalButton.setOnClickListener {

            if (!lastInp.isDigit() || isBracketOpened) {
                resultTxt.text = "Error: Incorrect syntax"
            } else {
                try {
                    val result = ExpressionBuilder(expression.toString()).build().evaluate()
                    val resToInt = result.toInt()
                    if ((result * 10).toInt() == resToInt * 10) {
                        resultTxt.text = resToInt.toString()
                    } else {
                        resultTxt.text = result.toString()
                    }
                } catch (e: Exception) {
                    resultTxt.text = e.message
                }
            }
        }
    }

    private fun onClick(view: View) {

        when(view.id) {
            R.id.one_button -> {
                expression.append('1')
                lastInp = '1'
            }
            R.id.two_button -> {
                expression.append('2')
                lastInp = '2'
            }
            R.id.three_button -> {
                expression.append('3')
                lastInp = '3'
            }
            R.id.four_button -> {
                expression.append('4')
                lastInp = '4'
            }
            R.id.five_button -> {
                expression.append('5')
                lastInp = '5'
            }
            R.id.six_button -> {
                expression.append('6')
                lastInp = '6'
            }
            R.id.seven_button -> {
                expression.append('7')
                lastInp = '7'
            }
            R.id.eight_button -> {
                expression.append('8')
                lastInp = '8'
            }
            R.id.nine_button -> {
                expression.append('9')
                lastInp = '9'
            }
            R.id.zero_button -> {
                if (expression.isNotEmpty()) {
                    expression.append('0')
                    lastInp = '0'
                }
            }
            R.id.add_button -> {
                if (lastInp.isDigit() || lastInp == ')') {
                    expression.append('+')
                    lastInp ='+'
                }
            }
            R.id.subtract_button -> {
                if (lastInp.isDigit() || lastInp == ')') {
                    expression.append('-')
                    lastInp = '-'
                }
            }
            R.id.divide_button -> {
                if (lastInp.isDigit() || lastInp == ')') {
                    expression.append('/')
                    lastInp = '/'
                }
            }
            R.id.multiply_button -> {
                if (lastInp.isDigit() || lastInp == ')') {
                    expression.append('*')
                    lastInp = '*'
                }
            }
            R.id.all_clear_button -> {
                if (expression.isNotEmpty()) {
                    expression.clear()
                    resultTxt.text = ""
                    lastInp = ' '
                }
            }
            R.id.open_bracket_button -> {
                if (lastInp != '.' || !lastInp.isDigit()) {
                    expression.append('(')
                    lastInp = '('
                    isBracketOpened = true
                }
            }
            R.id.close_bracket_button -> {
                if (isBracketOpened) {
                    expression.append(')')
                    lastInp = ')'
                    isBracketOpened = false
                }
            }
            R.id.dot_button -> {
                if (lastInp.isDigit()) {
                    expression.append('.')
                    lastInp = '.'
                }
            }
            R.id.go_back_button -> {
                if (expression.isNotEmpty()) {
                    expression.deleteCharAt(expression.length - 1)

                    lastInp = if (expression.isNotEmpty()) {
                        expression.elementAt(expression.length - 1)
                    } else {
                        ' '
                    }
                }
            }
        }

        inpTxt.text = expression.toString()
    }
}