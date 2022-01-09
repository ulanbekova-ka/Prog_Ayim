package com.kay.prog.ayim

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.kay.prog.ayim.databinding.ViewSeasonStateBinding

class SeasonStateView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding = viewBinding(ViewSeasonStateBinding::inflate)

    init {
        attrs.obtainStyledAttributes(context, R.styleable.SeasonStateView) {
            val seasonText = it.getString(R.styleable.SeasonStateView_ssvSeasonText) ?: ""
            binding.seasonTxt.text = seasonText
        }
        binding.btn.setOnClickListener {
            val date = binding.editText.text.toString().split(".")

            if (date.size == 3) {
                val day = date[0].toInt()
                val month = date[1].toInt()
                binding.seasonTxt.text = getSeason(day, month)
            } else {
                binding.seasonTxt.text = "Ошибка ввода"
            }
        }
    }

    private fun getSeason(day: Int, month : Int) : String {
        if (day > 31 || day < 0)
        {
            return "Неверная дата"
        }

        return when (month) {
            12, 1, 2 -> "Зима"
            3, 4, 5 -> "Весна"
            6, 7, 8 -> "Лето"
            9, 10, 11 -> "Осень"
            else -> "Неверная дата"
        }
    }
}