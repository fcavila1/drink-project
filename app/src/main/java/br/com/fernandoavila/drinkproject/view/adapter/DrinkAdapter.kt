package br.com.fernandoavila.drinkproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.fernandoavila.drinkproject.domain.Drink
import androidx.recyclerview.widget.RecyclerView
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.domain.DrinkDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.drink_list_lateral_item.view.*
//import kotlinx.android.synthetic.main.drink_list_lateral_item.view.ivThumb
//import kotlinx.android.synthetic.main.drink_list_lateral_item.view.tvTitle
import kotlinx.android.synthetic.main.drink_lista_2.view.*


class DrinkAdapter(private val dataSet: Array<Drink>, var clickListener: onDrinkClickListener) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>(){

    var drinkDet =  DrinkDetail(
        idDrink = null,
        strDrink = null,
        strDrinkThumb = null,
        strInstructions = null
    )

    //Cria o objeto baseado no formato do xml escolhido
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
//        println("ENTROU NO ADAPTER DOS DRINKS ")
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.drink_lista_2, parent, false)
        return DrinkViewHolder(view)
    }

    //Metodo que define quantos itens serao possiveis instanciar e apresentar na tela
    override fun getItemCount(): Int {
       return dataSet.size
    }

    //Pega os dados que o DrinkViewHolder tem e preenche na posicao da tela
    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = dataSet[position]

        holder.strDrink.text = drink.strDrink
        Picasso.get().load(drink.strDrinkThumb).into(holder.strDrinkThumb);


        holder.inicializar(dataSet.get(position), clickListener)

    }


    class DrinkViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val strDrinkThumb: ImageView = itemView.ivThumb
        val strDrink: TextView = itemView.tvTitle
        val buttonVerMais: Button = itemView.btnVerMais

        //antes era dataSet: Drink
        fun inicializar(dataSet: Drink, action:onDrinkClickListener){

            val buttonVerMais: Button = itemView.btnVerMais

            buttonVerMais.setOnClickListener{
                action.onDrinkClick(dataSet, adapterPosition)
            }
        }
    }

    interface onDrinkClickListener{
        fun onDrinkClick(dataSet: Drink, posicaoDrink: Int)
    }


}


