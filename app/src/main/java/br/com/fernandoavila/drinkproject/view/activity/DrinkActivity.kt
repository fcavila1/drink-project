package br.com.fernandoavila.drinkproject.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fernandoavila.drinkproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.drink_list_lateral_item.*


class DrinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drink_list_lateral_item)

        tvTitleDrink.text = getIntent().getStringExtra("Drinkname")
        Picasso.get().load(getIntent().getStringExtra("Drinkthumb")).into(ivDrinkThum);
        tvDescricao.text = getIntent().getStringExtra("DrinkInstr")


        btnVoltarMain.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }
}
