package com.example.Karyna_Labs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Lab6Activity extends Activity {
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    TextView latTextView, lonTextView, addressTextView, addressTextViewNew,distanceTextView;
    EditText address_lon, address_lat;
    double address_lon_d, address_lat_d;
    Button btnShowLocation, btnShowAddress, btnShowAddressNew, btnShowDistance;
    double latitude;
    double longitude;
    double dist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        btnShowAddress = (Button) findViewById(R.id.btnShowAddress);
        btnShowAddressNew = (Button) findViewById(R.id.btnShowAddressNew);
        btnShowDistance = (Button) findViewById(R.id.btnShowDistance);

        latTextView = findViewById(R.id.latTextView);
        lonTextView = findViewById(R.id.lonTextView);

        addressTextView = findViewById(R.id.addressTextView);
        addressTextViewNew = findViewById(R.id.addressTextViewNew);

        distanceTextView = findViewById(R.id.distanceTextView);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
            }
        });
        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
                addressTextView.setText(getAddress(latitude, longitude));
            }
        });
        btnShowAddressNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()) {
                    address_lon = findViewById(R.id.address_lon);
                    address_lat = findViewById(R.id.address_lat);
                    if (address_lon.getText() != null && address_lat.getText() != null) {
                        address_lon_d = Double.parseDouble(address_lon.getText().toString());
                        address_lat_d = Double.parseDouble(address_lat.getText().toString());
                    } else {
                        address_lon_d = 0;
                        address_lat_d = 0;
                    }
                    addressTextViewNew.setText(getAddress(address_lat_d, address_lon_d));
                }
            }
        });

        btnShowDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address_lon = findViewById(R.id.address_lon);
                address_lat = findViewById(R.id.address_lat);
                if (address_lon.getText() != null && address_lat.getText() != null) {
                    address_lon_d = Double.parseDouble(address_lon.getText().toString());
                    address_lat_d = Double.parseDouble(address_lat.getText().toString());
                } else {
                    address_lon_d = 0;
                    address_lat_d = 0;
                }
                dist=CalculationDistance(latitude,longitude, address_lat_d, address_lon_d);
                distanceTextView.setText(Double.toString(dist));
            }
        });
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String add = null;
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.v("IGA", "Address" + add);
            return add;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return add;
    }

    private static double CalculationDistance(double startPointLat, double startPointLon, double endPointLat, double endPointLon) {
        float[] results = new float[1];
        Location.distanceBetween(startPointLat, startPointLon, endPointLat, endPointLon, results);
        return results[0];
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                    latTextView.setText(latitude + "");
                                    lonTextView.setText(longitude + "");
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            latTextView.setText(latitude + "");
            lonTextView.setText(longitude + "");
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }
}


