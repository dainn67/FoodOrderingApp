package com.example.foodorderingapp.model

class Data {
    var totalCost: Double = 0.0

    private var mapAppetizer = mutableMapOf<Dish, Int>()
    private var mapMainDish = mutableMapOf<Dish, Int>()
    private var mapDessert = mutableMapOf<Dish, Int>()
    private var mapResult = mutableMapOf<Dish, Int>()

    fun getMapAppetizer() = mapAppetizer
    fun getMapMainDish() = mapMainDish
    fun getMapDessert() = mapDessert
    fun getMapResult() = mapResult
}

enum class FragmentType{
    APPETIZER,
    MAIN_DISH,
    DESSERT
}