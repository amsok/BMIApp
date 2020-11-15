package com.example.mybmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybmiapp.databinding.ActivityResultBinding

class Result : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            val bmiValue = intent.extras?.getDouble("bmi")
            textBmiValue.text = String.format("%.2f", bmiValue)
            if (bmiValue != null) {
                when {
                    bmiValue >= 25 -> {
                        imageView.setImageResource(R.drawable.fat)
                        bmiDescription.text = "Well, you are a big boi, it is time to lose some weight"
                    }
                    bmiValue < 18.5 -> {
                        imageView.setImageResource(R.drawable.slim)
                        bmiDescription.text = "Your BMI is really low, try to visit your grandma more often"
                    }
                    else -> {
                        imageView.setImageResource(R.drawable.ok)
                        bmiDescription.text = "Nice BMI, keep the shape"

                    }
                }
            }
        }




    }
}