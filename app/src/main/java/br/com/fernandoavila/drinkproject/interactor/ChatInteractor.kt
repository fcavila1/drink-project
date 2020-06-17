package br.com.fernandoavila.drinkproject.interactor

import android.content.ComponentCallbacks
import android.content.Context
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.domain.Mensagem
import br.com.fernandoavila.drinkproject.repository.ChatRepository

class ChatInteractor(private val context: Context) {

    private val chatRepository = ChatRepository(context, context.getString(R.string.heroku_base_api))

    fun enviarMensagem(mensagem: String, callback: (mensagem: String?) -> Unit) {
        chatRepository.enviarMensagem(mensagem, callback)
    }
}