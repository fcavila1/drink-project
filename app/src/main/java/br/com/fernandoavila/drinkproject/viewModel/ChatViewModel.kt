package br.com.fernandoavila.drinkproject.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.fernandoavila.drinkproject.domain.Mensagem
import br.com.fernandoavila.drinkproject.interactor.ChatInteractor

class ChatViewModel(val app: Application) : AndroidViewModel(app) {

    private val chatInteractor = ChatInteractor(app.applicationContext)

    val mensagemDialog = MutableLiveData<Mensagem>()

    fun enviarMensagem(mensagem: String) {
        chatInteractor.enviarMensagem(mensagem) { resposta ->
            if(resposta != null) {
                mensagemDialog.value = resposta
            }
        }
    }

}