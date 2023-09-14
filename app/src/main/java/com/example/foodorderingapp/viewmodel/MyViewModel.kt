package com.example.foodorderingapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.R
import com.example.foodorderingapp.model.Constants.Companion.TAG
import com.example.foodorderingapp.model.Data
import com.example.foodorderingapp.model.Dish

class MyViewModel : ViewModel() {
    //list for listViews
    private var appetizerList = mutableListOf<Dish>()
    private var mainList = mutableListOf<Dish>()
    private var dessertList = mutableListOf<Dish>()
    private var resultList = mutableListOf<Dish>()

    //maps from Data
    private var appetizerMap: MutableMap<Dish, Int>
    private var mainMap: MutableMap<Dish, Int>
    private var dessertMap: MutableMap<Dish, Int>
    private var resultMap: MutableMap<Dish, Int>

    //livedatas
    private var liveDataMapAppetizer: MutableLiveData<MutableMap<Dish, Int>> = MutableLiveData()
    private var liveDataMapMainDish: MutableLiveData<MutableMap<Dish, Int>> = MutableLiveData()
    private var liveDataMapDessert: MutableLiveData<MutableMap<Dish, Int>> = MutableLiveData()

    init {
        val data = Data()

        appetizerMap = data.getMapAppetizer()
        mainMap = data.getMapMainDish()
        dessertMap = data.getMapDessert()
        resultMap = data.getMapResult()

        addAppetizers()
        addMainDishes()
        addDesserts()

        liveDataMapAppetizer.value = appetizerMap
        liveDataMapMainDish.value = mainMap
        liveDataMapDessert.value = dessertMap
    }

    fun getAppetizerList() = appetizerList
    fun getMainDishList() = mainList
    fun getDessertList() = dessertList
    fun getResultList(): MutableList<Dish> {
        resultList.clear()
        for ((dish, amount) in appetizerMap) {
            if (amount > 0){
                resultList.add(dish)
                resultMap[dish] = amount
            }
        }
        for ((dish, amount) in mainMap) {
            if (amount > 0) {
                resultList.add(dish)
                resultMap[dish] = amount
            }
        }
        for ((dish, amount) in dessertMap) {
            if (amount > 0) {
                resultList.add(dish)
                resultMap[dish] = amount
            }
        }
        return resultList
    }

    fun getLiveDataMapAppetizer() = liveDataMapAppetizer
    fun getLiveDataMapMainDish() = liveDataMapMainDish
    fun getLiveDataMapDessert() = liveDataMapDessert

    fun getResultMap() = resultMap

    fun getTotal(): Double{
        var res = 0.0
//        for(dish in resultList){
//            res += resultMap[dish]?.times(dish.getPrice()) ?: 0.0
//        }
        for ((dish, amount) in resultMap){
            res += dish.getPrice() * amount
        }

        return String.format("%.2f", res).toDouble()
    }

    fun resetAll(){
        for((dish, amount) in appetizerMap)
            appetizerMap[dish] = 0
        for((dish, amount) in mainMap)
            mainMap[dish] = 0
        for((dish, amount) in dessertMap)
            dessertMap[dish] = 0
    }

    private fun addAppetizers() {
        var dish = Dish(
            R.drawable.chicken_nugget,
            "Chicken nuggets",
            "10 delicious & scrumpy pieces of nuggets from the finest chicken in USA",
            2.0
        )
        appetizerMap[dish] = 0
        appetizerList.add(dish)

        dish = Dish(
            R.drawable.french_fries,
            "French fries",
            "A box-full of delicious freshly fried potatoes",
            2.5
        )
        appetizerMap[dish] = 0
        appetizerList.add(dish)

        dish = Dish(
            R.drawable.onion_ring,
            "Onion rings",
            "Fresh onions covered by crispy flour",
            1.8
        )
        appetizerMap[dish] = 0
        appetizerList.add(dish)

        dish = Dish(
            R.drawable.fish_chip,
            "Fish & Chips",
            "Delicious traditional dished from the UK",
            2.5
        )
        appetizerMap[dish] = 0
        appetizerList.add(dish)

        liveDataMapAppetizer.value = appetizerMap
    }
    private fun addDesserts() {
        var dish = Dish(
            R.drawable.ice_cream,
            "Ice cream",
            "Cold and fresh cup of ice cream",
            1.0
        )
        dessertMap[dish] = 0
        dessertList.add(dish)

        dish = Dish(
            R.drawable.chocolate_cookies,
            "Chocolate cookies",
            "Baked cookies made from dark chocolate",
            1.0
        )
        dessertMap[dish] = 0
        dessertList.add(
            dish
        )

        dish = Dish(
            R.drawable.jelly,
            "Jelly bowl",
            "Bouncing sweet bowl of jelly",
            2.5
        )
        dessertMap[dish] = 0
        dessertList.add(dish)

        dish = Dish(
            R.drawable.tiramisu,
            "Tiramisu",
            "It's a Tiramisu cake",
            1.0
        )
        dessertMap[dish] = 0
        dessertList.add(dish)

        dish = Dish(
            R.drawable.flan,
            "Flan cake",
            "A special cake made from flour with a top of melted caramel",
            2.0
        )
        dessertMap[dish] = 0
        dessertList.add(dish)
    }
    private fun addMainDishes() {
        var dish = Dish(
            R.drawable.fried_rice,
            "Spicy fried rice",
            "A wonderful mixture of fried rice, cheese, sausages and 11 exotic flavour",
            2.5
        )
        mainMap[dish] = 0
        mainList.add(dish)

        dish = Dish(
            R.drawable.drumsticks,
            "Fried chicken drumstick",
            "2 huge drumsticks covered with a heaven of flavour",
            2.0
        )
        mainMap[dish] = 0
        mainList.add(dish)

        dish = Dish(
            R.drawable.wings,
            "Chicken wings",
            "A set of 6 sweet & sour fried wings for the family",
            4.0
        )
        mainMap[dish] = 0
        mainList.add(dish)

        dish = Dish(
            R.drawable.hamburger,
            "Chicken hamburger",
            "A big size hamburger with chicken, bacon, salad and eggs",
            2.5
        )
        mainMap[dish] = 0
        mainList.add(dish)

        dish = Dish(
            R.drawable.burrito,
            "Burrito",
            "Traditional spicy delicious burrito from the one and only Mexico",
            2.5
        )
        mainMap[dish] = 0
        mainList.add(dish)
    }

}