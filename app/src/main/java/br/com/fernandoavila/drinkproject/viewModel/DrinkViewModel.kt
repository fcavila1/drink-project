package br.com.fernandoavila.drinkproject.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.fernandoavila.drinkproject.domain.Drink
import br.com.fernandoavila.drinkproject.domain.DrinkDetail
import br.com.fernandoavila.drinkproject.interactor.DrinkInteractor
import br.com.fernandoavila.drinkproject.repository.dto.DrinkDTO

class DrinkViewModel (val app: Application) : AndroidViewModel(app) {

    private val iteractor = DrinkInteractor(app.applicationContext)

    //
    val result = MutableLiveData<Array<Drink>>()
    var bebida =  DrinkDetail(
        idDrink = null,
        strDrink = null,
        strDrinkThumb = null,
        strInstructions = null
    )

    fun listaDrinksGin(){
        iteractor.listaDrinksGin { drinks ->
            val drinkFinal = mutableListOf<Drink>()

            drinks.forEach { drink ->
//                val newDrink = Drink(
//                    idDrink = drink.idDrink,
//                    strDrink = drink.
//                )
                drinkFinal.add(drink)
            }

            result.value = drinkFinal.toTypedArray()
        }
    }


    fun detalhesDrink(idDrink:String, callback: (drink: DrinkDetail) -> Unit){
        println("DRINK ID VIEW MODEL: $idDrink")
        iteractor.detalhesDrink(idDrink){ drink ->
            callback(drink)
        }
    }


}