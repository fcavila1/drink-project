package br.com.fernandoavila.drinkproject.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.view.adapter.ChatAdapter
import br.com.fernandoavila.drinkproject.viewModel.ChatViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main.*

class ChatActivity : AppCompatActivity() {

    private val chatViewModel: ChatViewModel  by lazy {
        ViewModelProvider(this).get(ChatViewModel::class.java)
    }

    private val adapter = ChatAdapter(this)

    private val navegacao = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_chat -> {

                val intentChat = Intent(this, ChatActivity::class.java)
                startActivity(intentChat)
            }
            R.id.navigation_home -> {
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
            }
            R.id.navigation_map -> {
                val intentMap= Intent(this, MapsActivity::class.java)
                startActivity(intentMap)
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        nav.setOnNavigationItemSelectedListener(navegacao)

        sendButton.setOnClickListener { enviarMensagem() }
        mudancasNaListaDeRespostas()

    }

    //Metodo para configurar a apresentacao(No caso esta vertical um abaixo do outro)
    private fun mudancasNaListaDeRespostas(){
        rvChat.layoutManager = LinearLayoutManager(this)
        chatViewModel.mensagemDialog.observe(this, Observer { resposta ->
            rvChat.adapter = adapter
            enviarListaMensagens(resposta)
        })
    }

    //Envia as mensagens para a viewModel
    private fun enviarMensagem(){
        val mensagem = inputChat.text.toString()
        enviarListaMensagens(mensagem);
        inputChat.editableText.clear()
        chatViewModel.enviarMensagem(mensagem)
    }

    //Envia as mensagens para o array de mensagens do adapter
    private  fun enviarListaMensagens(msg: String) {
        val adapter = ChatAdapter(this);
        adapter.adicionarMensagemLista(msg)
    }

}
