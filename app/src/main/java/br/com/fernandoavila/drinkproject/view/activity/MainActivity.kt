package br.com.fernandoavila.drinkproject.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fernandoavila.drinkproject.R
import br.com.fernandoavila.drinkproject.domain.Drink
import br.com.fernandoavila.drinkproject.domain.DrinkDetail
import br.com.fernandoavila.drinkproject.view.adapter.DrinkAdapter
import br.com.fernandoavila.drinkproject.viewModel.DrinkViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drink_lista_2.*
import java.io.File
import com.google.android.material.bottomnavigation.BottomNavigationView as BottomNavigationView

class MainActivity : AppCompatActivity(), DrinkAdapter.onDrinkClickListener{

    private val viewModel: DrinkViewModel by lazy {
        ViewModelProvider(this).get(DrinkViewModel::class.java)
    }

    private val navegacao = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_chat -> {
                val intentChat = Intent(this, ChatActivity::class.java)
                startActivity(intentChat)
            }
            R.id.navigation_home -> {
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
            }
            R.id.navigation_map -> {
                val intentMap = Intent(this, MapsActivity::class.java)
                startActivity(intentMap)
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showDrinks()
        configureReciclerView()

        val nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        nav.setOnNavigationItemSelectedListener(navegacao)

    }

    private fun showDrinks(){
        viewModel.result.observe(this, Observer { drinks ->
            val adapter = DrinkAdapter(drinks, this)
               rvDrinks.adapter = adapter
        })

        viewModel.listaDrinksGin()
    }


    override fun onDrinkClick(dataSet: Drink, posicaoDrink: Int) {
        viewModel.detalhesDrink(dataSet.idDrink.toString()){drink ->
          println("VOLTOU PARA A ACTIVITY:" + drink)
        val intentSingleDrink = Intent(this, DrinkActivity::class.java)
        intentSingleDrink.putExtra("Drinkname", drink.strDrink)
        intentSingleDrink.putExtra("Drinkthumb", drink.strDrinkThumb)
        intentSingleDrink.putExtra("DrinkInstr", drink.strInstructions)
        startActivity(intentSingleDrink)
        }
    }

    //Metodo para configurar a apresentacao(No caso esta vertical um abaixo do outro)
    private fun configureReciclerView(){
        rvDrinks.layoutManager = LinearLayoutManager(this)
    }


}

