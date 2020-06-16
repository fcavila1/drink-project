package br.com.fernandoavila.drinkproject.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.viewModel.AuthViewModel

import kotlinx.android.synthetic.main.activity_cadastrar.*
import java.lang.Exception

class CadastrarActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this).get(AuthViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        viewModel.mensagemCadastrar.observe(this, Observer { resposta->
            if(resposta.mensagem != ""){
                Toast.makeText(this, resposta.mensagem, Toast.LENGTH_LONG).show()
                if(resposta.status){
                    val intentCompletarCadastro = Intent(this, CompletarCadastroActivity::class.java)
                    startActivity(intentCompletarCadastro)
                }
            }
        })

        cadastroBtnCadastrar.setOnClickListener { realizarCadastro() }
    }

    private fun realizarCadastro(){
        val email = cadastroInputEmail.text.toString()
        val senha = cadastroInputSenha.text.toString()
        val senha2 = cadastroInputRepSenha.text.toString()
        try {
            viewModel.cadastrar(email, senha, senha2)
        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}
