package com.ckc.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.ckc.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.darkNight.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked){ AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{ AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) } }

        binding.calculateButton.setOnClickListener {calculate()}

    }
    private fun calculate(){

        val input = binding.costOfServiceEditText.text.toString().toDoubleOrNull()

        if(input==0.0||input==null){
            displayTip(0.0)
            return
        }

        val checked =when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = checked*input

        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){ tip =kotlin.math.ceil(tip) }
        displayTip(tip)

    }
    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip) //money format convert
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }

}