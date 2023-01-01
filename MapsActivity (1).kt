package eniso.ia2.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Criteria
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import eniso.ia2.myapplication.databinding.ActivityMapsBinding
import java.security.Provider

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sousse = LatLng(35.82, 10.64)
        mMap.addMarker(MarkerOptions().position(sousse).title("Marker in Sousse"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sousse,7F))
        val tunis = LatLng(36.8, 10.17)
        mMap.addMarker(MarkerOptions().position(tunis).title("Marker in Tunis"))
        mMap.mapType=GoogleMap.MAP_TYPE_HYBRID
        val options=PolylineOptions()
        options.add(LatLng(36.8,10.17),
                    LatLng(35.82,10.64))
        mMap.addPolyline(options)
        mMap.setOnMapClickListener {
            Toast.makeText(this,"latitude est ${it.latitude} \n longitude est ${it.longitude}",Toast.LENGTH_LONG).show()
        }
        mMap.setOnMarkerClickListener {
            if (it.position==sousse){
                val uri=Uri.parse("http://wikepedia.ord/wiki/sousse")
                val i=Intent(Intent.ACTION_VIEW,uri)
                startActivity(i)
            }
            if (it.position==tunis){0
                val uri=Uri.parse("http://wikepedia.ord/wiki/tunis")
                val i=Intent(Intent.ACTION_VIEW,uri)
                startActivity(i)
            }
        true }
        //1)
        val locationManager=getSystemService(LOCATION_SERVICE) as LocationManager
        //2) Définir les critères
        val criteria=Criteria()
        criteria.accuracy=Criteria.ACCURACY_FINE
        criteria.isCostAllowed=true
        val best=locationManager.getBestProvider(criteria,true)
        Toast.makeText(this,"$best",Toast.LENGTH_LONG).show()
        val location=locationManager.getLastKnownLocation(best!!)
        if (location!=null){
            val lat =location.latitude
            val long =location.longitude
            Toast.makeText(this,"$lat;$long",Toast.LENGTH_LONG).show()
        }
        else {Toast.makeText(this,"null location",Toast.LENGTH_LONG).show()}

    }
}