package br.com.fernandoavila.drinkproject.domain

data class Drink(
    val idDrink: String? = null,
    val strDrink: String? = null,
    val strDrinkThumb: String? = null
)

data class DrinkDetail(
    var idDrink: String? = null,
    var strDrink: String? = null,
    var strDrinkThumb: String? = null,
    var strInstructions: String? = null
)