package com.example.foodorderingapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.foodorderingapp.R
import com.example.foodorderingapp.model.Dish

class ResultAdapter(
    private val context: Context,
    private val id: Int,
    private val list: List<Dish>,
    private val map: MutableMap<Dish, Int>
) : ArrayAdapter<Dish>(context, id, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val myInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = convertView ?: myInflater.inflate(id, null, false)

        val dish = list[position]

        view.findViewById<ImageView>(R.id.resImg).setImageResource(dish.getImg())
        view.findViewById<TextView>(R.id.tvResName).text = dish.getName()
        val tvDesc = view.findViewById<TextView>(R.id.tvResDesc)
        tvDesc.text = dish.getDesc()
        tvDesc.isSelected = true
        view.findViewById<TextView>(R.id.tvResPrice).text = "$${dish.getPrice()}"
        view.findViewById<TextView>(R.id.tvResNumber).text = "Amount: ${map[dish]}"

        return view
    }
}
