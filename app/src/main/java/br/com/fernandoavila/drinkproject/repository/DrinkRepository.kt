package br.com.fernandoavila.drinkproject.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import br.com.fernandoavila.drinkproject.domain.Drink
import br.com.fernandoavila.drinkproject.domain.DrinkDetail
import br.com.fernandoavila.drinkproject.repository.dto.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import br.com.fernandoavila.drinkproject.view.activity.MainActivity
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Headers

interface DrinkService{

    //ANTIGO
    //@Headers("x-rapidapi-key: bdfa34d303msh55dd252f7128c85p1087c3jsnce458099aa3d")
    @GET("filter.php")
    fun listaDrinksGin(
        @Query("i") filtroGin: String = "Gin"
    ): Call<DrinkListDTO>
    //Converte o json em objeto


    @GET("lookup.php")
    fun drinkDetail(
        @Query("i") idDrink: String
    ): Call<DetalhesDrinkDTO>

}

class DrinkRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    //cria um objeto que o retrofit mapeou da interface
    private val service = retrofit.create(DrinkService::class.java)

    fun listaDrinksGin(callback: (drinks: Array<Drink>) -> Unit) {

        //Conexao inicia com o ENQUEUE
        //Objeto que retorna é o Callback DrinkListDTO
        service.listaDrinksGin().enqueue(object : Callback<DrinkListDTO> {

            //Resposta do tipo DrinkListDTO e Chamada
            override fun onResponse(call: Call<DrinkListDTO>, response: Response<DrinkListDTO>) {
                //Objeto de apresentacao
                val result = mutableListOf<Drink>()
                //Lista de DTO
                val drinks = response.body()?.drinks
                println("DRINKS****************************"+ drinks)

                    //Percorre a lista de DTO
                    drinks?.forEach { drink ->
                        //COnverte o DTO para um objeto de DOMAIN
                        val domain = Drink(
                            idDrink = drink.idDrink,
                            strDrink = drink.strDrink,
                            strDrinkThumb = drink.strDrinkThumb
                        )

                        result.add(domain)


                    }
                //Callback para a interactor
                callback(result.toTypedArray())
            }

            override fun onFailure(call: Call<DrinkListDTO>, t: Throwable) {

                callback(arrayOf())
            }
        })

    }

    fun drinkDetail(idDrink: String, callback: (drinkDetail: DrinkDetail) -> Unit){
        service.drinkDetail(idDrink).enqueue(object : Callback<DetalhesDrinkDTO> {
            override fun onResponse(call: Call<DetalhesDrinkDTO>, response: Response<DetalhesDrinkDTO>) {
                val drink = response.body()?.drinks
                val domain = DrinkDetail(
                    idDrink = drink?.get(0)?.idDrink,
                    strDrink = drink?.get(0)?.strDrink,
                    strDrinkThumb = drink?.get(0)?.strDrinkThumb,
                    strInstructions = drink?.get(0)?.strInstructions
                )
                println("***************AQUI**********************"+ domain)
                callback(domain)
            }

            override fun onFailure(call: Call<DetalhesDrinkDTO>, error: Throwable) {
//                callback(DrinkDetail())
            }
        })

    }

}