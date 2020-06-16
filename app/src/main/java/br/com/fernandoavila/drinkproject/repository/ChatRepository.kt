package br.com.fernandoavila.drinkproject.repository

import android.content.Context
import br.com.fernandoavila.drinkproject.domain.Mensagem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatService{

    @POST("/api/message/text/send")
    fun enviarMensagem(
        @Body mensagem: Mensagem
    ): Call<Mensagem>
    //Converte o json em objeto

}

class ChatRepository(context: Context, baseUrl: String):BaseRetrofit(context, baseUrl) {

    private val service: ChatService = retrofit.create(ChatService::class.java)

    fun enviarMensagem(mensagem: String, callback: (mensagem: Mensagem?) -> Unit){

        service.enviarMensagem(Mensagem(mensagem)).enqueue(object: Callback<Mensagem> {
            override fun onResponse(call: Call<Mensagem>, response: Response<Mensagem>) {

                callback(response.body())
            }

            override fun onFailure(call: Call<Mensagem>, t: Throwable) {

                callback(null)
            }
        })
    }
}