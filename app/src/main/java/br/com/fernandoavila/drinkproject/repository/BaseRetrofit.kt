package br.com.fernandoavila.drinkproject.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Coloca-se o OPEN para que essa classe possa ser herdada
open class BaseRetrofit(context: Context, baseUrl: String) {
    val retrofit: Retrofit
    val gson: Gson

    init {
        //Interceptador de Requisicoes
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //Aqui devo informar ao retrofit o cliente que vai usar o interceptador de requisicoes e constroi o objeto cliente
        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        //constroi o objeto de gson, que transforma Json em objeto
        gson = GsonBuilder().create()

        //Constroi o objeto de retrofit mostrando o cliente e que ele tem interceptador, a base url e o conversor de Json para
        //objeto
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

}