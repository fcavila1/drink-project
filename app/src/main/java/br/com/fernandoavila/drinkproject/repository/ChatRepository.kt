package br.com.fernandoavila.drinkproject.repository

import android.content.Context
import br.com.fernandoavila.drinkproject.domain.Mensagem
import br.com.fernandoavila.drinkproject.repository.dto.respotaDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatService{

    @POST("/api/message/text/send")
    fun enviarMensagem(
        @Body mensagem: Mensagem
    ): Call<List<respotaDialog>>
    //Converte o json em objeto

}

class ChatRepository(context: Context, baseUrl: String):BaseRetrofit(context, baseUrl) {

    private val service: ChatService = retrofit.create(ChatService::class.java)

    fun enviarMensagem(mensagem: String, callback: (mensagem: String?) -> Unit){

        service.enviarMensagem(Mensagem(mensagem)).enqueue(object: Callback<List<respotaDialog>> {
            override fun onResponse(call: Call<List<respotaDialog>>, response: Response<List<respotaDialog>>) {

                val result = response.body()
//                println("==============================>" +  result)
                var mensagem:String? = null
                result?.forEach { m ->
                    if(m != null){
                        if(m.queryResult?.fulfillmentText != null){
                            mensagem = m.queryResult?.fulfillmentText.toString()
//                            println("MENSAGEM==============================>" +  message)
                        }
                    }
                }
                callback(mensagem)
//                callback(response.body())
            }

            override fun onFailure(call: Call<List<respotaDialog>>, t: Throwable) {

                callback(null)
            }
        })
    }
}