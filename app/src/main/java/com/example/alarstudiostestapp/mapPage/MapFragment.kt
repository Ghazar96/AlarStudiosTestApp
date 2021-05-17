package com.example.alarstudiostestapp.mapPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alarstudiostestapp.R
import com.example.alarstudiostestapp.mainPage.LAT_KEY
import com.example.alarstudiostestapp.mainPage.LNG_KEY
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    private var map: GoogleMap? = null
    private var lat: Double = 0.0
    private var lng: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_map,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        lat = arguments?.getDouble(LAT_KEY, 0.0) ?: 0.0
        lng = arguments?.getDouble(LNG_KEY, 0.0) ?: 0.0
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val location = LatLng(lat, lng)
        map?.addMarker(
            MarkerOptions()
                .position(location)
                .title("Marker in Sydney")
        )
        map?.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}