package br.com.fernandoavila.drinkproject.domain

import java.util.*

data class Mensagem(val text: String){
    var email: String = "teste@teste.com"
    var sessionId: String = "8asdbad7"
    constructor(text: String, email: String, sessionId: String): this(text){
        this.email = email
        this.sessionId = sessionId
    }
}
