package br.com.fernandoavila.drinkproject.interactor

import android.content.Context
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.domain.Drink
import br.com.fernandoavila.drinkproject.domain.DrinkDetail
import br.com.fernandoavila.drinkproject.repository.DrinkRepository

class DrinkInteractor(private val context: Context) {
    private val drinkRepository = DrinkRepository(context, context.getString(R.string.drink_db_base_url))

    fun detalhesDrink(idDrink: String, callback: (drink: DrinkDetail) -> Unit) {
       val idDrinkInteiro = idDrink.toInt();
        if (idDrinkInteiro < 0) {
            throw Exception(context.getString(R.string.invalid_drink_id))
        }
        drinkRepository.drinkDetail(idDrink , callback)
    }

    fun listaDrinksGin(callback: (drinks: Array<Drink>) -> Unit) {
        drinkRepository.listaDrinksGin(callback)
    }

}