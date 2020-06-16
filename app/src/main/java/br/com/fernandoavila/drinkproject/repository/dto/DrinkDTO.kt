package br.com.fernandoavila.drinkproject.repository.dto

data class DrinkDTO(
    val idDrink: String? = null,
    val strDrink: String? = null,
    val strDrinkThumb: String? = null
)

data class DrinkDetailDTO(
    val idDrink: String? = null,
    val strDrink: String? = null,
    val strDrinkThumb: String? = null,
    val strInstructions: String? = null
)

data class DrinkListDTO(
    val drinks: Array<DrinkDTO>? = null
)
data class DetalhesDrinkDTO(
    val drinks: Array<DrinkDetailDTO>? = null
)

