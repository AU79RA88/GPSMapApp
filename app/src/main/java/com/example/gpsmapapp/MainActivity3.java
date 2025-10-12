package com.example.gpsmapapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity
        implements OnMapReadyCallback {

    private EditText txtLatitud, txtLongitud;
    private GoogleMap mMap;
    private final List<LatLng> puntosGuardados = new ArrayList<>();
    private FusedLocationProviderClient fusedLocationClient;
    private final LatLng chile = new LatLng(-30.5920917, -71.2468779);
    private Handler handler = new Handler();
    private boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(chile).title("Chile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chile, 10f));

        habilitarUbicacionPrecisa();


        mMap.setOnMapLongClickListener(latLng -> {

            handler.postDelayed(() -> {
                if (isPressed) {
                    puntosGuardados.add(latLng);
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Punto guardado " + puntosGuardados.size()));

                    txtLatitud.setText(String.format("%.6f", latLng.latitude));
                    txtLongitud.setText(String.format("%.6f", latLng.longitude));

                    Toast.makeText(this, "Punto guardado #" + puntosGuardados.size(), Toast.LENGTH_SHORT).show();
                }
            }, 4000); // 4 segundos

            isPressed = true;
        });


        mMap.setOnMapClickListener(latLng -> isPressed = false);
    }

    @SuppressLint("MissingPermission")
    private void habilitarUbicacionPrecisa() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1001
            );
            return;
        }

        mMap.setMyLocationEnabled(true);

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(2000);

        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        Location location = locationResult.getLastLocation();
                        if (location != null) {
                            double lat = location.getLatitude();
                            double lon = location.getLongitude();

                            txtLatitud.setText(String.format("%.6f", lat));
                            txtLongitud.setText(String.format("%.6f", lon));

                            if (mMap != null && puntosGuardados.isEmpty()) {
                                LatLng actual = new LatLng(lat, lon);
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(actual, 17f));
                            }
                        }
                    }
                },
                getMainLooper()
        );
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1001 &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            habilitarUbicacionPrecisa();
        } else {
            Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
        }
    }
}
