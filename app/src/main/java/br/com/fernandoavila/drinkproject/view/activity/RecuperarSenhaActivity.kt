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
import kotlinx.android.synthetic.main.activity_recuperar_senha.*

class RecuperarSenhaActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_senha)

        viewModel.mensagemEsqueciSenha.observe(this, Observer { resposta->
            if(resposta.mensagem != ""){
                Toast.makeText(this, resposta.mensagem, Toast.LENGTH_LONG).show()
                if(resposta.status){
                    val intentLogin = Intent(this, LoginActivity::class.java)
                    startActivity(intentLogin)
                }
            }
        })
        recuperarSenhaBtnEnviar.setOnClickListener { recuperarSenha() }
    }

    private fun recuperarSenha(){
        val email = recuperarSenhaInputEmail.text.toString()

        try {
            viewModel.recuperarSenha(email)
        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }

}
