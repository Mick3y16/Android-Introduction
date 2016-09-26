package com.example.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity implements LocationListener {

    /**
     * TextView displaying user's latitude.
     */
    private TextView latitude;

    /**
     * TextView displaying user's longitude.
     */
    private TextView longitude;

    /**
     * Manages the location of the user.
     */
    private LocationManager locationManager;

    /**
     * Provider of the user's location.
     */
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        this.latitude = (TextView) findViewById(R.id.latitude);
        this.longitude = (TextView) findViewById(R.id.longitude);

        // Gets the location manager
        this.locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Defines the criteria to select the position provider.
        Criteria criteria = new Criteria();
        this.provider = this.locationManager.getBestProvider(criteria, false);

        Location location = this.locationManager.getLastKnownLocation(provider);

        // Sets the initial location
        if (location != null) {
            System.out.println(provider + " was selected.");
            onLocationChanged(location);
        } else {
            this.latitude.setText("Location unavailable.");
            this.longitude.setText("Location unavailable.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.locationManager.requestLocationUpdates(this.provider, 400, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.latitude.setText(String.valueOf(location.getLatitude()));
        this.longitude.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        // Do nothing...
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(this, "Enabled new provider: " + this.provider, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(this, "Disabled provider: " + this.provider, Toast.LENGTH_SHORT).show();
    }
}
