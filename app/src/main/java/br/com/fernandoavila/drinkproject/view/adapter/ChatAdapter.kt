package br.com.fernandoavila.drinkproject.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fernandoavila.drinkproject.R
import kotlinx.android.synthetic.main.mensagem.view.*

class ChatAdapter(private val context: Context): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private var mensagens = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.mensagem, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mensagens.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val mensagem = mensagens[position]
        holder.mensagem.text = mensagem
    }

    fun adicionarMensagemLista(msg: String){
        mensagens.add(msg)
        notifyDataSetChanged()
    }

    class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mensagem: TextView = itemView.tvMensagem
    }


}