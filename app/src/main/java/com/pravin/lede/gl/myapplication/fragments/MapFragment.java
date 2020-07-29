package com.pravin.lede.gl.myapplication.fragments;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.utils.MyDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener {


    private GoogleMap mMap;
    private LocationManager locationManager;
    private MyDatabase myDatabase;
    private LatLng previousLocation;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        FragmentManager fragmentManager = getChildFragmentManager();
        SupportMapFragment supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        myDatabase = new MyDatabase(getActivity(), 1);
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            checkAndRequestLocation();
        } else {
            Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_LONG).show();
        }
    }

    private void checkAndRequestLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 2, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        checkAndRequestLocation();
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.map_style));
        // Add a marker in Sydney and move the camera
        LatLng sakoli = new LatLng(21.085861, 79.98888);
        mMap.addMarker(new MarkerOptions().position(sakoli).title("Marker in Sakoli"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sakoli));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getActivity(), "Location changed", Toast.LENGTH_SHORT).show();
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            Date date = new Date();
            if (previousLocation != null && previousLocation.latitude != latLng.latitude && previousLocation.longitude != latLng.longitude) {
                myDatabase.insertLocation(location.getLatitude(), location.getLongitude(), date.toString());
            }
            previousLocation = latLng;
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void drawLocations() {
        ArrayList<LatLng> arrayList = myDatabase.getAllLocation();
        if(arrayList.size()!=0){
            LatLng previousLocation = arrayList.get(0);
            for (LatLng latLng : arrayList) {
                mMap.addPolyline(new PolylineOptions().color(Color.BLACK).width(5.0f).add(previousLocation, latLng));
                previousLocation = latLng;
            }
        }
    }
}
