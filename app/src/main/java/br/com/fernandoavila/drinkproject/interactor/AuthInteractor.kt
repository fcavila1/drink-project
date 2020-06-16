package br.com.fernandoavila.drinkproject.interactor

import android.content.Context
import android.provider.ContactsContract
import br.com.fernandoavila.drinkproject.domain.Profile
import br.com.fernandoavila.drinkproject.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class AuthInteractor(private val context: Context) {

    private val repository = AuthRepository(context)
    private val mAuth = FirebaseAuth.getInstance()

    fun logar(email: String, senha: String, callback: (resposta: Task<AuthResult>) -> Unit){

        if(email.isEmpty()){
            throw Exception("Campo E-mail obrigatório.")
        }
        if(senha.isEmpty()){
            throw Exception("Campo Senha obrigatório.")
        }
        if(senha.length < 6){
            throw Exception("Senha deve conter no mínimo 6 caracteres.")
        }
        repository.logar(email, senha, callback)
    }

    fun cadastrar(email: String, senha: String, senha2:String, callback: (resposta: Task<AuthResult>) -> Unit){
        if(email.isEmpty()){
            throw Exception("Campo E-mail obrigatório.")
        }
        if(senha.isEmpty()){
            throw Exception("Campo Senha obrigatório.")
        }
        if(senha2.isEmpty()){
            throw Exception("Campo Confirmar Senha obrigatório.")
        }
        if(senha.length < 6){
            throw Exception("Senha deve conter no mínimo 6 caracteres.")
        }
        if(senha2.length < 6){
            throw Exception("Confirmação de Senha deve conter no mínimo 6 caracteres.")
        }
        if(senha != senha2){
            throw Exception("Os campos Senha devem ser iguais.")
        }
        repository.cadastrar(email, senha, senha2, callback)
    }

    fun recuperarSenha(email: String, callback: (resposta: Task<Void>) -> Unit){
        if(email.isEmpty()){
            throw Exception("O campo e-mail é obrigatório.")
        }
        repository.recuperarSenha(email, callback)
    }

    fun salvar(profile: Profile, callback: (resposta: Boolean) -> Unit){
        val uid = mAuth.currentUser?.uid
        if(profile.name?.isEmpty()!!){
            throw Exception("Campo Nome obrigatório.")
        }
        if(profile.phone?.isEmpty()!!){
            throw Exception("Campo Telefone obrigatório.")
        }
        if(uid == null){
            throw Exception("Não foi possível recuperar a chave do usuário.")
        }

        if (uid != null) {
            repository.salvar(profile,uid, callback)
        }
    }
}