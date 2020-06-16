package br.com.fernandoavila.drinkproject.view.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.fernandoavila.drinkproject.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    // VARIAVEIS PARA ACHAR LOCALIZACAO ATUAL DO USUARIO
//    var locationManager: LocationManager? = null
//    private val permissoesNecessarias = arrayOf(
//        Manifest.permission.ACCESS_COARSE_LOCATION
//    )


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
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        nav.setOnNavigationItemSelectedListener(navegacao)



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap



        // Add a marker in Sydney and move the camera
//        val bsb = LatLng(-15.7801, -47.9292)
//        mMap.addMarker(MarkerOptions().position(bsb).title("Marker in Brasília"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bsb, 11f))
        val bsb = LatLng(-15.7882292, -47.9005925)
        //mMap.addMarker(MarkerOptions().position(bsb).title("Marker in Brasília"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bsb, 12.5f))

        val ivv_swinebar = LatLng(-15.7441851, -47.8946346)
        mMap.addMarker(MarkerOptions().position(ivv_swinebar).title("IVV Swinebar").snippet("CLN 214, Loja 21")).showInfoWindow()

        val bambamba = LatLng(-15.7598654, -47.8789437)
        mMap.addMarker(MarkerOptions().position(bambamba).title("Bambambã").snippet("SCLN 408, Bloco E, loja 94"))

        val gps_lifetime = LatLng(-15.7878865, -47.8830689)
        mMap.addMarker(MarkerOptions().position(gps_lifetime).title("GPS Lifetime").snippet("SCN Q 1 - Asa Norte"))

        val duo_drink = LatLng(-15.7869677, -47.9169881)
        mMap.addMarker(MarkerOptions().position(duo_drink).title("DUO Drink").snippet("St. Sudoeste Quadra Mista Sudoeste 2"))

        val essence_lounge_bar = LatLng(-15.8146913, -47.8896618)
        mMap.addMarker(MarkerOptions().position(essence_lounge_bar).title("Essence Lounge Bar").snippet("EQS 404/405"))

        val fora_do_eixo = LatLng(-15.8155739, -47.9026866)
        mMap.addMarker(MarkerOptions().position(fora_do_eixo).title("Fora do Eixo").snippet("CLS 108 BL C Loja 01"))

        val balcony = LatLng(-15.8309592, -47.911436)
        mMap.addMarker(MarkerOptions().position(balcony).title("BalcoNY").snippet("SCLS 412 Loja 17"))

        val buraco_do_jazz = LatLng(-15.8065103, -47.9247719)
        mMap.addMarker(MarkerOptions().position(buraco_do_jazz).title("Buraco do Jazz").snippet("Srps Estacionamento 05 - Parque da Cidade"))

        val dudu_bar = LatLng(-15.8036147, -47.892285)
        mMap.addMarker(MarkerOptions().position(dudu_bar).title("Dudu Bar").snippet("SCLS 303, Bloco A"))

        val noah_garden_bar = LatLng(-15.8230732, -47.9007393)
        mMap.addMarker(MarkerOptions().position(noah_garden_bar).title("Noah Garden Bar").snippet("SHCS CLS 408 Bloco C Loja 25"))

        val stadt_bar = LatLng(-15.796316, -47.9114145)
        mMap.addMarker(MarkerOptions().position(stadt_bar).title("Stadt Bar").snippet("St. de Industrias Graficas BL G"))

        val delta_bar = LatLng(-15.7700044, -47.8918666)
        mMap.addMarker(MarkerOptions().position(delta_bar).title("Delta Bar").snippet("SCRN 706, Via W 4 Norte, 6 - 707 Bl. E"))

        val uinto_bar = LatLng(-15.7832251, -47.8825217)
        mMap.addMarker(MarkerOptions().position(uinto_bar).title("5uinto Bar").snippet("SCLN 102 Bloco A, Loja 56"))

        val royal_drinks = LatLng(-15.7621572, -47.8825486)
        mMap.addMarker(MarkerOptions().position(royal_drinks).title("Royal Drinks").snippet("Asa Norte SQN 208 Royal Drinks, BL F"))

        val victrola_vinil = LatLng(-15.747159, -47.8832352)
        mMap.addMarker(MarkerOptions().position(victrola_vinil).title("Victrola Vinil Bar").snippet("Asa Norte Comércio Local Norte 413 BL E"))

        val nazo_sushi_bar = LatLng(-15.7953507, -47.9165375)
        mMap.addMarker(MarkerOptions().position(nazo_sushi_bar).title("Nazo Sushi Bar").snippet("SIG Quadra 8 Lote 2375"))

        val mercadito_bar = LatLng(-15.8047606, -47.8852308)
        mMap.addMarker(MarkerOptions().position(mercadito_bar).title("Mercadito Bar").snippet("Asa Sul Comércio Local Sul 202 BL A"))
    }

}
