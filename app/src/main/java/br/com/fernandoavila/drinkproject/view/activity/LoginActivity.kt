package br.com.fernandoavila.drinkproject.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.viewModel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.mensagemLogin.observe(this, Observer { resposta->
            if(resposta.mensagem != ""){
                Toast.makeText(this, resposta.mensagem, Toast.LENGTH_LONG).show()
                if(resposta.status){
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)
                }
            }
        })

        loginTxtCadastrar.setOnClickListener { cadastrar() }
        loginTxtEsqueciSenha.setOnClickListener { recuperarSenha() }
        loginButtonAcessar.setOnClickListener { logar() }
    }

    private fun cadastrar(){
        val intentCadastrar = Intent(this, CadastrarActivity::class.java)
        startActivity(intentCadastrar)
    }

    private fun recuperarSenha(){
        val intentRecuperarSenha =  Intent(this, RecuperarSenhaActivity::class.java)
        startActivity(intentRecuperarSenha)
    }

    private fun logar(){
        val email = loginInputEmail.text.toString()
        val senha = loginInputSenha.text.toString()
        try {
            viewModel.logar(email, senha)
        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}
