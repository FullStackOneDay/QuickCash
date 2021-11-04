package com.example.quick_cash_grp13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.quick_cash_grp13.databinding.ActivityMapBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseDatabase database;
    private DatabaseReference jobRef;
    private boolean mLocationPermission = false;
    private ActivityMapBinding binding;
    private SearchView mSearchText;
    private String TAG = "Maptag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mSearchText = (SearchView) findViewById(R.id.input_search);

        initializeDatabase();
        initMap();

        //allow user to search on map
        mSearchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String locationString = mSearchText.getQuery().toString();
                LatLng location = latLngFromString(locationString)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });



    }

    /**
     * Manipulates the map once available.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //this will add a pin to the map for every job in the database
        jobRef.addChildEventListener(new ChildEventListener() {
            private ArrayList<Marker> markers = new ArrayList<>();

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Job job = dataSnapshot.getValue(Job.class);
                String locationString = job.getLocation();
                LatLng location = latLngFromString(locationString);

                //add a marker to the map, and add the returned marker to a list of markers
                markers.add(mMap.addMarker(new MarkerOptions().position(location).title(job.getJobTitle()).snippet("Salary" + job.getSalary())));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "The read failed: " + error.getCode());
            }


        });

    }


    private void initMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    private void initializeDatabase() {
        database = FirebaseDatabase.getInstance();
         jobRef = database.getReference("jobs");

    }

    //Searches the input string to find a google maps address.
    private LatLng latLngFromString(String locationString) {
        List<Address> addressList = null;
        Geocoder geo = new Geocoder(MapActivity.this);

        if(locationString != null || !locationString.equals("")) {
            try {
                addressList = geo.getFromLocationName(locationString, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            return latLng;
        }
      return null;
    }



}