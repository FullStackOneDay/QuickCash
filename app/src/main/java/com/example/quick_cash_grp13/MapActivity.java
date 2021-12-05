package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.quick_cash_grp13.databinding.ActivityMapBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private final int DEFAULT_ZOOM = 13;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private GoogleMap mMap;
    private FirebaseDatabase database;
    private DatabaseReference jobRef;
    private boolean mLocationPermission = false;


    private ActivityMapBinding binding;
    private EditText mSearchText;
    private String TAG = "Maptag";
    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mSearchText = (EditText) findViewById(R.id.input_search);
        getLocationPermission();

        initializeDatabase();


    }

    /**
     * Manipulates the map once available.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (mLocationPermission) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            // Invoke Search Location service
            searchInitialize();

        }

    }


    private void initMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }


    private void initializeDatabase() {
        database = FirebaseDatabase.getInstance();
        jobRef = database.getReference("jobs");

    }



    private void getLocationPermission() {

        String[] permissions = {FINE_LOCATION, COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermission = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions, LOCATION_PERMISSION_REQUEST_CODE);

            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions, LOCATION_PERMISSION_REQUEST_CODE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: Requesting for permissions");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermission = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermission = false;
                            return;
                        }
                    }
                    mLocationPermission = true;
                    Log.d(TAG, "onRequestPermissionsResult: permissions given by user");
                    //initialize our map
                    initMap();
                }
            }
        }
    }

    public void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: starts");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermission) {
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();
                            if (currentLocation != null) {
                                moveCamera(new LatLng(currentLocation.getLatitude(),
                                                currentLocation.getLongitude()),
                                        DEFAULT_ZOOM, "current location");
                            } else
                                Log.d(TAG, "getDeviceLocation: Current location is null");
                        } else {

                            Toast.makeText(MapActivity.this, "Unable to get current location",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException se) {
            Log.d(TAG, "getDeviceLocation: SecurityException: =" + se.getMessage());
        }
    }

    public void moveCamera(LatLng latlng, float zoom, String title) {
        Log.d(TAG, "moveCamera: starts with latitude: " + latlng.latitude + " and Longitude: " +
                latlng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
        MarkerOptions options = new MarkerOptions()
                .position(latlng)
                .title(title);
        mMap.addMarker(options);
        hideSoftKeyboard();
    }

    private void searchInitialize() {
        Log.d(TAG, "init starts");
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER) {
                    // perform search
                    geoLocate();
                }
                return false;
            }
        });
    }

    private void geoLocate() {
        Log.d(TAG, "GeoLocate: starts");
        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder((MapActivity.this));
        List<Address> addressLists = new ArrayList<>();
        try {
            addressLists = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException ex) {
        }
        if (addressLists.size() > 0) {
            Address address = addressLists.get(0);
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()),
                    DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }



    }

    private void hideSoftKeyboard(){


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}