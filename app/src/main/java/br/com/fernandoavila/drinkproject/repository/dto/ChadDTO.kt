package br.com.fernandoavila.drinkproject.repository.dto

data class respotaDialog(
    val queryResult: resultadoQuery? = null
)
data class resultadoQuery(
    val fulfillmentText: String? = null
)