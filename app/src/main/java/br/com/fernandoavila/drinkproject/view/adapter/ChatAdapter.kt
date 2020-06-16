package br.com.fernandoavila.drinkproject.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.domain.Drink
import br.com.fernandoavila.drinkproject.domain.Mensagem
import kotlinx.android.synthetic.main.mensagem.view.*

class ChatAdapter(private val context: Context): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private var mensagens = ArrayList<Mensagem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.mensagem, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mensagens.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val mensagem = mensagens[position]

        holder.mensagem.text = mensagem.text
    }

    class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mensagem: TextView = itemView.tvMensagem
    }

    fun adicionarMensagemLista(msg: Mensagem){
        mensagens.add(msg)
        notifyDataSetChanged()
    }
}