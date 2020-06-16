package br.com.fernandoavila.drinkproject.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fernandoavila.drinkproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_completar_cadastro.*
import br.com.fernandoavila.drinkproject.domain.Profile
import br.com.fernandoavila.drinkproject.viewModel.AuthViewModel
import java.lang.Exception

class CompletarCadastroActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    private val profile: Profile? = null

    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completar_cadastro)

        verificacao()
    }

    private fun verificacao(){
        //Recupera o email do usuário atual e insere no campo email
        val email =  mAuth.currentUser?.email
        completarCadastroInputEmail.setText(email)

        viewModel.mensagemCompletarCadastro.observe(this, Observer { resposta->
            if(resposta.mensagem != ""){
                Toast.makeText(this, resposta.mensagem, Toast.LENGTH_LONG).show()
                if(resposta.status){
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)
                }
            }
        })

        //Executar o metodo salvar ao clicar no botao de salvar
        completarCadastroBtnSalvar.setOnClickListener { salvar()}

    }

    private fun salvar(){
        //Cria um objeto com os dados da tela do tipo Profile
        val profile = Profile(
            email = completarCadastroInputEmail.text.toString(),
            name = completarCadastroInputNome.text.toString(),
            phone = completarCadastroInputTelefone.text.toString()
        )

        try {
            viewModel.salvar(profile)
        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }









    }
}
