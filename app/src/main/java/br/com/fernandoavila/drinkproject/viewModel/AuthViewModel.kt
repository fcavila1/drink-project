package br.com.fernandoavila.drinkproject.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.fernandoavila.drinkproject.domain.Profile
import br.com.fernandoavila.drinkproject.domain.RespostaReq
import br.com.fernandoavila.drinkproject.interactor.AuthInteractor
import kotlinx.android.synthetic.main.activity_completar_cadastro.*

class AuthViewModel (val app: Application) : AndroidViewModel(app){

    private val interactor = AuthInteractor(app.applicationContext)
    val mensagemLogin = MutableLiveData<RespostaReq>()
    val mensagemCadastrar = MutableLiveData<RespostaReq>()
    val mensagemCompletarCadastro = MutableLiveData<RespostaReq>()
    val mensagemEsqueciSenha = MutableLiveData<RespostaReq>()

    fun logar(email: String, senha: String){
        interactor.logar(email, senha){resposta ->
            if(resposta.isSuccessful){
                mensagemLogin.value = RespostaReq(true, "Login Realizado com Sucesso.")
            }else{
                val error = resposta.exception?.localizedMessage ?: "Não foi possível entrar no aplicativo no momento."
                mensagemLogin.value = RespostaReq(false, error)
            }
        }
    }

    fun cadastrar(email: String, senha: String, senha2: String){
        interactor.cadastrar(email,senha,senha2){resposta ->
            if(resposta.isSuccessful){
                mensagemCadastrar.value = RespostaReq(true, "Cadastro Realizado com Sucesso.")
            }else{
                val error = resposta.exception?.localizedMessage ?: "Não foi possível realizar o cadastro no momento."
                mensagemCadastrar.value = RespostaReq(false, error)
            }
        }
    }

    fun recuperarSenha(email: String){
        interactor.recuperarSenha(email){resposta->
            if(resposta.isSuccessful){
                mensagemEsqueciSenha.value = RespostaReq(true, "O e-mail para recuperação de senha foi enviado..")
            }else{
                val error = resposta.exception?.localizedMessage ?: "Não foi possível enviar o e-mail no momento."
                mensagemEsqueciSenha.value = RespostaReq(false, error)
            }
        }
    }

    fun salvar(profile: Profile){
        interactor.salvar(profile){ resposta->
            if(resposta){
                mensagemCompletarCadastro.value = RespostaReq(true, "Cadastro finalizado com sucesso.")
            }else{
                mensagemCompletarCadastro.value = RespostaReq(false,  "Não foi possível recuperar a chave do usuário.")
            }
        }

    }


}