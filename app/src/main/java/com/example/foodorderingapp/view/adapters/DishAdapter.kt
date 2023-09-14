package com.example.foodorderingapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.foodorderingapp.R
import com.example.foodorderingapp.model.Constants.Companion.INITIAL_AMOUNT
import com.example.foodorderingapp.model.Dish
import com.example.foodorderingapp.model.FragmentType
import com.example.foodorderingapp.viewmodel.MyViewModel

class DishAdapter(
    private val fragmentType: FragmentType,
    private var owner: LifecycleOwner,
    private val myViewModel: MyViewModel,
    private val context: Context,
    private val id: Int,
) : ArrayAdapter<Dish>(
    context,
    id,
    when (fragmentType) {
        FragmentType.APPETIZER -> myViewModel.getAppetizerList()
        FragmentType.MAIN_DISH -> myViewModel.getMainDishList()
        FragmentType.DESSERT -> myViewModel.getDessertList()
    }
) {
    //get the current fragment's map (livedata)
    private val myLiveDataMap = when (fragmentType) {
        FragmentType.APPETIZER -> myViewModel.getLiveDataMapAppetizer()
        FragmentType.MAIN_DISH -> myViewModel.getLiveDataMapMainDish()
        FragmentType.DESSERT -> myViewModel.getLiveDataMapDessert()
    }
    private var myMap = myLiveDataMap.value

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var amount = INITIAL_AMOUNT

        //inflate the item's view
        val myInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = convertView ?: myInflater.inflate(id, null, false)

        //minus, amount and plus button
        val imgMinus = view.findViewById<ImageView>(R.id.itemMinusImg)
        val tvAmount = view.findViewById<TextView>(R.id.itemAmount)
        val imgPlus = view.findViewById<ImageView>(R.id.itemPlusImg)

        //get the dish from the list
        val dish = when (fragmentType) {
            FragmentType.APPETIZER -> myViewModel.getAppetizerList()[position]
            FragmentType.MAIN_DISH -> myViewModel.getMainDishList()[position]
            FragmentType.DESSERT -> myViewModel.getDessertList()[position]
        }

        //view binding
        view.findViewById<ImageView>(R.id.itemImg).setImageResource(dish.getImg())
        view.findViewById<TextView>(R.id.itemName).text = dish.getName()
        val tvDesc = view.findViewById<TextView>(R.id.itemDesc)
        tvDesc.text = dish.getDesc()
        tvDesc.isSelected = true
        view.findViewById<TextView>(R.id.itemPrice).text = "$${dish.getPrice()}"

        val checkBoxOrder = view.findViewById<CheckBox>(R.id.itemCheckBox)
        val llAmount = view.findViewById<LinearLayout>(R.id.itemLLAmount)

        //observer
        val myObserver = Observer<MutableMap<Dish, Int>> { newMap ->
            myMap = newMap
            if (myMap!![dish] != 0) {
                checkBoxOrder.isChecked = true
                llAmount.visibility = View.VISIBLE
                tvAmount.text = myMap!![dish].toString()
            } else {
                checkBoxOrder.isChecked = false
                llAmount.visibility = View.GONE
            }
        }

        //observe changes
        myLiveDataMap.observe(owner, myObserver)

        //set checkbox's behaviour
        checkBoxOrder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                myMap!![dish] = 1
                myLiveDataMap.value = myMap     //notify changed
            } else {
                myMap!![dish] = 0
                myLiveDataMap.value = myMap     //notify changed
            }
        }

        imgMinus.setOnClickListener {
            if (amount > 1) amount--
            myMap!![dish] = amount
            myLiveDataMap.value = myMap     //notify changed
        }
        imgPlus.setOnClickListener {
            if (amount < 10) amount++
            myMap!![dish] = amount
            myLiveDataMap.value = myMap     //notify changed
        }

        return view
    }
}