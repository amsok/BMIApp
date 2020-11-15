package com.example.mybmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*
import com.example.mybmiapp.databinding.ActivityHistoryBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class History : AppCompatActivity() {
    private var historyList = ArrayList<Item>()
    lateinit var binding:ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonString = intent.extras?.getString("historyList")
        val type = object : TypeToken<List<Item>>() {}.type
        historyList =  Gson().fromJson(jsonString, type)



        binding.apply {
            val adapter = BMIAdapter(historyList)
            rvHistory.adapter = adapter
            rvHistory.layoutManager = LinearLayoutManager(this@History)
        }

    }
}