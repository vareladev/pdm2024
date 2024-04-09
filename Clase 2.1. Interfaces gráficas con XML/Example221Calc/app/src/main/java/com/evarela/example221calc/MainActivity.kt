package com.evarela.example221calc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.evarela.example221calc.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var equation = "0"
        val tvEquation = binding.tvEquation
        tvEquation.text = equation
        val tvResult = binding.tvResult
        tvResult.text = ""

        binding.btnZero.setOnClickListener {
            equation+="0"
            tvEquation.text = equation
        }

        binding.btnDel.setOnClickListener {
            equation = "0"
            tvEquation.text = equation
        }

        //.....

    }
}