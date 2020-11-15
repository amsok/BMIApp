package com.example.mybmiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmiapp.databinding.ItemBmiBinding

class BMIAdapter(
    var items: List<Item>,
): RecyclerView.Adapter<BMIAdapter.BMIViewHolder>() {
    lateinit var binding: ItemBmiBinding
    inner class BMIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BMIViewHolder {
        binding = ItemBmiBinding.inflate(LayoutInflater.from(parent.context))
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bmi, parent, false)
        for (i in items){

        }
        return BMIViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(items.size < 10)
            items.size
        else
            10
    }

    override fun onBindViewHolder(holder: BMIViewHolder, position: Int) {
        binding.apply {
            holder.itemView.apply {
                tvBmi.text = items[position].bmi.toString()
                tvHeight.text = items[position].height.toString()
                tvMass.text = items[position].mass.toString()
                tvDate.text = items[position].date
            }

        }

    }
}