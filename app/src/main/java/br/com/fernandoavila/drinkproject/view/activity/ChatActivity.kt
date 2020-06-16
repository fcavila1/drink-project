package br.com.fernandoavila.drinkproject.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.domain.Mensagem
import br.com.fernandoavila.drinkproject.view.adapter.ChatAdapter
import br.com.fernandoavila.drinkproject.view.adapter.DrinkAdapter
import br.com.fernandoavila.drinkproject.viewModel.ChatViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_main.*

class ChatActivity : AppCompatActivity() {

    private val chatViewModel: ChatViewModel  by lazy {
        ViewModelProvider(this).get(ChatViewModel::class.java)
    }

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

        observarMudancaLista()
    }

    private fun observarMudancaLista(){
        val adapter = ChatAdapter(this);
        rvChat.adapter = adapter
        chatViewModel.mensagemDialog.observe(this, Observer { resposta -> enviarListaMensagens(resposta) })
    }


    private fun enviarMensagem(){
        val mensagem = inputChat.text.toString()
        inputChat.editableText.clear()

        enviarListaMensagens(Mensagem(mensagem));

        chatViewModel.enviarMensagem(mensagem)
    }

    private  fun enviarListaMensagens(msg: Mensagem) {
        val adapter = ChatAdapter(this);
        adapter.adicionarMensagemLista(msg)
    }

}
