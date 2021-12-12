package com.kay.prog.ayim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kay.prog.ayim.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var expression = StringBuilder("")
    private var lastInp = ' '
    private var isBracketOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            oneButton.setOnClickListener(::onClick)
            twoButton.setOnClickListener(::onClick)
            threeButton.setOnClickListener(::onClick)
            fourButton.setOnClickListener(::onClick)
            fiveButton.setOnClickListener(::onClick)
            sixButton.setOnClickListener(::onClick)
            sevenButton.setOnClickListener(::onClick)
            eightButton.setOnClickListener(::onClick)
            nineButton.setOnClickListener(::onClick)
            zeroButton.setOnClickListener(::onClick)
            addButton.setOnClickListener(::onClick)
            subtractButton.setOnClickListener(::onClick)
            multiplyButton.setOnClickListener(::onClick)
            divideButton.setOnClickListener(::onClick)
            dotButton.setOnClickListener(::onClick)
            allClearButton.setOnClickListener(::onClick)
            openBracketButton.setOnClickListener(::onClick)
            closeBracketButton.setOnClickListener(::onClick)
            goBackButton.setOnClickListener(::onClick)

            equalButton.setOnClickListener {
                if (!lastInp.isDigit() || isBracketOpened) {
                    solutionTxt.text = "Error: Incorrect syntax"
                } else {
                    try {
                        val result = ExpressionBuilder(expression.toString()).build().evaluate()
                        val resToInt = result.toInt()
                        if ((result * 10).toInt() == resToInt * 10) {
                            solutionTxt.text = resToInt.toString()
                        } else {
                            solutionTxt.text = result.toString()
                        }
                    } catch (e: Exception) {
                        solutionTxt.text = e.message
                    }
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
                    binding.solutionTxt.text = ""
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

        binding.inputTxt.text = expression.toString()
    }
}