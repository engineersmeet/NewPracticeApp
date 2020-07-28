package com.pravin.lede.gl.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pravin.lede.gl.myapplication.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements LocationListener {

    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private ArrayList<LatLng> storedLocationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        requestLocationService();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(onMapReadyCallback);

    }

    private void requestLocationService() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
        } else {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000,
                    200, this);
        }
    }

    OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng positionOne = new LatLng(21.085861, 79.98888);
            setLocationWithMarker(positionOne);
        }
    };

    ArrayList<LatLng> savedLocationsList = new ArrayList<>();

    private void setLocationWithMarker(LatLng latLng) {
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(18.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("My Current Location"));
                mMap.addCircle(new CircleOptions().center(latLng).radius(500).strokeWidth(0.0f).fillColor(R.color.colorLightBlue));
                savedLocationsList.add(latLng);
                showLocations(500, latLng);
            }
        });
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void showLocations(int meters, LatLng currentLatLng){
        for(LatLng latLng: savedLocationsList){
            if(getDistance(latLng, currentLatLng) <= meters){
                mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.addPolyline(new PolylineOptions().width(5.0f).add(latLng, currentLatLng).color(Color.BLUE));
            }
        }
    }

    private double getDistance(LatLng latLng1, LatLng latLng2){
        Location loc1 = new Location("");
        loc1.setLatitude(latLng1.latitude);
        loc1.setLongitude(latLng1.longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(latLng2.latitude);
        loc2.setLongitude(latLng2.longitude);

        return loc1.distanceTo(loc2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            requestLocationService();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
            //setLocationWithMarker(sydney);
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


}
