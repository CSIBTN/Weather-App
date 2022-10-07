package com.csibtn.qforecast.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.csibtn.qforecast.R
import com.csibtn.qforecast.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.markodevcic.peko.Peko
import com.markodevcic.peko.PermissionResult
import kotlinx.coroutines.launch

class MapFragment : Fragment(), OnMapReadyCallback {
    private var isPermissionGranted = false
    private val mapArgs: MapFragmentArgs by navArgs()
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMapBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        checkIfPermissionGranted()

        if (isPermissionGranted)
            mapFragment.getMapAsync(this)
        return binding.root
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val placeLocation = LatLng(mapArgs.latitude.toDouble(), mapArgs.longitude.toDouble())
        Log.d("Location", "${mapArgs.latitude},${mapArgs.longitude}")
        map.addMarker(MarkerOptions().position(placeLocation).title("Your place marker"))
        map.moveCamera(CameraUpdateFactory.newLatLng(placeLocation))
    }

    private fun checkIfPermissionGranted() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result =
                Peko.requestPermissionsAsync(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            val result2 = Peko.requestPermissionsAsync(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            if (result is PermissionResult.Granted && result2 is PermissionResult.Granted) {
                isPermissionGranted = true
            }
        }
    }


}