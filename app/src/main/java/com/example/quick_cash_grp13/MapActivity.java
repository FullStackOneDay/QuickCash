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
                String location = mSearchText.getQuery().toString();
                List<Address> addressList = null;
                if(location != null || !location.equals("")) {
                    Geocoder geo = new Geocoder(MapActivity.this);
                    try {
                        addressList = geo.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng exactLoc = new LatLng(address.getLatitude(), address.getLongitude());
                    //move camera to the submitted address
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(exactLoc));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
                }
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
        //not yet working
        jobRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Job job = dataSnapshot.getValue(Job.class);

                String location = job.getLocation();
                System.out.println(location);
                //get job address from location string
                List<Address> addressList = null;
                if(location != null || !location.equals("")) {
                    Geocoder geo = new Geocoder(MapActivity.this);
                    try {
                        addressList = geo.getFromLocationName(location, 1);
                        Address address = addressList.get(0);
                        LatLng exactLoc = new LatLng(address.getLatitude(), address.getLongitude());
                        //add a marker for the job containing its title and pay
                        mMap.addMarker(new MarkerOptions().position(exactLoc).title(job.getJobTitle()).snippet("Salary" + job.getSalary()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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

//    Job job = new Job("TESTTitle", "TESTCompany", "TEST",  "6299 South St, Halifax", 1000);
//    String location = job.getLocation();
//
//    //get job address from location string
//    List<Address> addressList = null;
//    if(location != null || !location.equals("")) {
//        Geocoder geo = new Geocoder(MapActivity.this);
//        try {
//            addressList = geo.getFromLocationName(location, 1);
//            Address address = addressList.get(0);
//            LatLng exactLoc = new LatLng(address.getLatitude(), address.getLongitude());
//            //add a marker for the job containing its title and pay
//            mMap.addMarker(new MarkerOptions().position(exactLoc).title(job.getJobTitle()).snippet("Salary: " + job.getSalary()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

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



}