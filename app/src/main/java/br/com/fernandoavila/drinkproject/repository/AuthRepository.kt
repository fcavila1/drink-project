package br.com.fernandoavila.drinkproject.repository

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.widget.Toast
import br.com.fernandoavila.drinkproject.domain.Profile
import br.com.fernandoavila.drinkproject.view.activity.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthRepository(context: Context) {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()

    fun logar(email: String, senha: String, callback: (resposta: Task<AuthResult>) -> Unit){
        val operacao = mAuth.signInWithEmailAndPassword(email,senha)
        operacao.addOnCompleteListener { resposta ->
            callback(resposta)
        }
    }

    fun cadastrar(email: String, senha: String, senha2: String, callback: (resposta: Task<AuthResult>) -> Unit){
        val operacao = mAuth.createUserWithEmailAndPassword(email,senha)
        operacao.addOnCompleteListener{resposta->
            callback(resposta)
        }
    }

    fun recuperarSenha(email: String, callback: (resposta: Task<Void>) -> Unit){
        val operacao = mAuth.sendPasswordResetEmail(email)
        operacao.addOnCompleteListener{resposta->
            callback(resposta)
        }
    }

    fun salvar(profile: Profile, uid: String, callback: (resposta: Boolean) -> Unit){
            //Recupera a referencia para o nó do usuario atual
            val userProfile = db.getReference("profiles/$uid")
            userProfile.setValue(profile)
            callback(true)

    }
}