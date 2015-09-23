package com.example.sandilemazibuko.grooveapp_beta_01;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LocalStorage userDatabase;

    public static final Double WORK_LAT = -26.185014;
    public static final Double WORK_LONG = 28.020134;
    public static final int DEFAULT_ZOOM = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        // Add a marker in Sydney and move the camera




        LatLng sydney = new LatLng(WORK_LAT, WORK_LONG);
        mMap.addMarker(
                new MarkerOptions()
                        .position(sydney)
                        .title("Harm: 160 Jan Smuts Ave, Johannesburg, 2196")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, DEFAULT_ZOOM));

        LatLng sydney2 = new LatLng(WORK_LAT+1, WORK_LONG+1);
        mMap.addMarker(new MarkerOptions()
                .position(sydney2)
                .title("Kong:1 Jackson Rd, Central, Hong Kong")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));


        LatLng sydney3 = new LatLng(WORK_LAT+2, WORK_LONG+2);
        mMap.addMarker(new MarkerOptions()
                .position(sydney3)
                .title("Bungalow: Glen Country Club, 3 Victoria Rd, Clifton, Cape Town, 8005")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));


        LatLng sydney4 = new LatLng(WORK_LAT+4, WORK_LONG+4);
        mMap.addMarker(new MarkerOptions()
                .position(sydney4)
                .title("Vip: Cnrner Witkoppen & Rivonia Road, Rivonia Crossing, 3 Achter Rd, Johannesburg\n")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));

        LatLng sydney5 = new LatLng(WORK_LAT+5, WORK_LONG+5);
        mMap.addMarker(new MarkerOptions()
                .position(sydney5)
                .title("Coccon:24 Central,Cnr Fredman Drive and Gwen Lane,, Sandown, Sandton, Johannesburg, 2192")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));

        LatLng sydney6 = new LatLng(WORK_LAT+6, WORK_LONG+6);
        mMap.addMarker(new MarkerOptions()
                .position(sydney6)
                .title("Fight club:60 revonia Rd .\n" +
                        "Basement bryanpark shopping centre cnr cumberland n grosvenor rd Bryanston .\n" +
                        "5 star junction honey dew cnr beyers naude n juice street honey dew .\n" +
                        "2nd floor eagle house \n" +
                        "39 somrest road, Greenpoint captown\n" +
                        "9/3/2015 10:30: +27 84 211 8383: Shop 15 melrose arch high street Johannesburg\n")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));

        LatLng sydney7 = new LatLng(WORK_LAT+7, WORK_LONG+7);
        mMap.addMarker(new MarkerOptions()
                .position(sydney7)
                .title("Taboo : 24 Central cnr freeman drive n gwen lane Sandown sandton Johannesburg 2192")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));



        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }


    /**
     * -------------------------------------------------------------------------------------------------------------
     * MENU NAVIGATION
     * -------------------------------------------------------------------------------------------------------------
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        userDatabase = new LocalStorage(this);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MapsActivity.this, "Settings", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_about_this_app){
            Toast.makeText(MapsActivity.this, "About This App", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_instructions){
            Toast.makeText(MapsActivity.this, "App Instructions", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_faqs){
            Toast.makeText(MapsActivity.this, "FAQ's", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_logout){
            userDatabase.clearUserData();
            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
