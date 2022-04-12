package com.example.maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.example.maps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.time.LocalTime;
import java.util.Calendar;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button button;
    private String userType;
    private String permitType;
    private LatLng agganis;
    private LatLng langsam;
    private LatLng buick;
    private LatLng cfa;
    private LatLng essex;
    private LatLng lowerbridge;
    private LatLng upperbridge;
    private LatLng cas;
    private LatLng warren;
    private LatLng fiveseventyfive;
    private LatLng rafik;
    private LatLng kenmore;
    private LatLng seventhirtysevenfifty;
    private LatLng sevensixtysix;
    private LatLng eightninety;
    private LatLng orange1;
    private LatLng orange2;
    private LatLng orange3;
    private LatLng orange4;
    private LatLng granby;
    LocalTime time;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Start Usertype activity when button is clicked
        button = (Button)findViewById(R.id.select_permit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUsertypeActivity();
            }
        });

        // Receive permit information
        Intent intent = getIntent();
        userType = intent.getStringExtra("user");
        permitType = intent.getStringExtra("permit");
    }

    public void openUsertypeActivity(){
        // Show user type selection screen
        Intent intent = new Intent(this, UsertypeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Time now
        time = LocalTime.now();

        // Day of week
        calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);


        // move the camera to BU
        LatLng bu = new LatLng(42.351139402544476, -71.10977147739284);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bu,14.0f));

        // assign locations (latitude/longitude) of lots
        agganis = new LatLng(42.352355809859226, -71.11772127450622);
        langsam = new LatLng(42.35331905254574, -71.1228019601163);
        buick = new LatLng(42.35210382678141, -71.11502725640673);
        cfa = new LatLng(42.35153882813562, -71.1138312447708);
        essex = new LatLng(42.349960881193674, -71.11125763128003);
        lowerbridge = new LatLng(42.35165834340202, -71.1102084564067);
        upperbridge = new LatLng(42.35143293081643, -71.10983037360717);
        cas = new LatLng(42.350765458988604, -71.104642052697);
        warren = new LatLng(42.34939648328429, -71.10392795826158);
        fiveseventyfive = new LatLng(42.34957384140391, -71.09867751593434);
        rafik = new LatLng(42.349783603220565, -71.09967648895285);
        kenmore = new LatLng(42.349473070577055, -71.09761386778928);
        seventhirtysevenfifty = new LatLng(42.34986499037443, -71.106231908515);
        sevensixtysix = new LatLng(42.3501319315078, -71.10809568709799);
        eightninety = new LatLng(42.35077184298448, -71.11552617175231);
        orange1 = new LatLng(42.34774843721593, -71.1058815527099);
        orange2 = new LatLng(42.3479953595156, -71.10395584477165);
        orange3 = new LatLng(42.34788127817424, -71.10331807360758);
        orange4 = new LatLng(42.34880365168017, -71.10709471778706);
        granby = new LatLng(42.348908184482376, -71.09799848954573);

        displayMarkers(time, day_of_week);

    }

    public void displayMarkers(LocalTime time, int day_of_week){
        // display markers at currently valid lots based on permit, time & day

        if (userType == null){
            allMarkers();
        } else if (userType.equals("Student")){
            if (permitType.equals("Flex")){
                studentFlex(time, day_of_week);
            } else if (permitType.equals("Commuter")){
                studentCommuter(time, day_of_week);
            } else if (permitType.equals("Evening")){
                studentEvening(time, day_of_week);
            } else if (permitType.equals("Orange")){
                studentOrange();
            } else if (permitType.equals("Langsam")){
                studentLangsam();
            }
        } else if (userType.equals("Employee")){
            if (permitType.equals("Flex")){
                employeeFlex();
            } else if (permitType.equals("Commuter")){
                employeeCommuter();
            } else if (permitType.equals("Off-peak commuter")){
                employeeOffPeakCommuter(time, day_of_week);
            } else if (permitType.equals("Carpool")){
                employeeCarpool();
            }
        } else if (userType.equals("Guest")) {
            if (permitType.equals("Agganis Arena Event")) {
                guestAgganis();
            } else if (permitType.equals("Red Sox")) {
                guestRedSox();
            } else if (permitType.equals("Non-Event")) {
                guestNonEvent();
            }
        }
    }

    /* START OF HELPER FUNCTIONS TO DISPLAY MARKERS*/
    public void allMarkers(){
        mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
        mMap.addMarker(new MarkerOptions().position(buick).title("Buick Street Lot"));
        mMap.addMarker(new MarkerOptions().position(cfa).title("CFA Lot"));
        mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
        mMap.addMarker(new MarkerOptions().position(lowerbridge).title("Lower Bridge Lot"));
        mMap.addMarker(new MarkerOptions().position(upperbridge).title("Upper Bridge Lot"));
        mMap.addMarker(new MarkerOptions().position(cas).title("CAS Lot"));
        mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
        mMap.addMarker(new MarkerOptions().position(fiveseventyfive).title("575 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
        mMap.addMarker(new MarkerOptions().position(kenmore).title("Kenmore Lot"));
        mMap.addMarker(new MarkerOptions().position(seventhirtysevenfifty).title("730/750 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(sevensixtysix).title("766 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(eightninety).title("890 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(orange1).title("Street Parking"));
        mMap.addMarker(new MarkerOptions().position(orange2).title("Street Parking"));
        mMap.addMarker(new MarkerOptions().position(orange3).title("Street Parking"));
        mMap.addMarker(new MarkerOptions().position(orange4).title("Street Parking"));
        mMap.addMarker(new MarkerOptions().position(granby).title("Granby Lot"));


    }
    public void employeeCommuter(){
        mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
        mMap.addMarker(new MarkerOptions().position(buick).title("Buick Street Lot"));
        mMap.addMarker(new MarkerOptions().position(cfa).title("CFA Lot"));
        mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
        mMap.addMarker(new MarkerOptions().position(lowerbridge).title("Lower Bridge Lot"));
        mMap.addMarker(new MarkerOptions().position(upperbridge).title("Upper Bridge Lot"));
        mMap.addMarker(new MarkerOptions().position(cas).title("CAS Lot"));
        mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
        mMap.addMarker(new MarkerOptions().position(fiveseventyfive).title("575 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
        mMap.addMarker(new MarkerOptions().position(kenmore).title("Kenmore Lot"));
        mMap.addMarker(new MarkerOptions().position(seventhirtysevenfifty).title("730/750 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(sevensixtysix).title("766 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(eightninety).title("890 Commonwealth Avenue"));
    }
    public void employeeFlex(){
        mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
        mMap.addMarker(new MarkerOptions().position(buick).title("Buick Street Lot"));
        mMap.addMarker(new MarkerOptions().position(cfa).title("CFA Lot"));
        mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
        mMap.addMarker(new MarkerOptions().position(lowerbridge).title("Lower Bridge Lot"));
        mMap.addMarker(new MarkerOptions().position(upperbridge).title("Upper Bridge Lot"));
        mMap.addMarker(new MarkerOptions().position(cas).title("CAS Lot"));
        mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
        mMap.addMarker(new MarkerOptions().position(fiveseventyfive).title("575 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
        mMap.addMarker(new MarkerOptions().position(kenmore).title("Kenmore Lot"));
        mMap.addMarker(new MarkerOptions().position(seventhirtysevenfifty).title("730/750 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(sevensixtysix).title("766 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(eightninety).title("890 Commonwealth Avenue"));
    }
    public void employeeOffPeakCommuter(LocalTime time, int day){
        if (day > 5 || time.isAfter(LocalTime.of(2,30,0))){
            mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
            mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
            mMap.addMarker(new MarkerOptions().position(buick).title("Buick Street Lot"));
            mMap.addMarker(new MarkerOptions().position(cfa).title("CFA Lot"));
            mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
            mMap.addMarker(new MarkerOptions().position(lowerbridge).title("Lower Bridge Lot"));
            mMap.addMarker(new MarkerOptions().position(upperbridge).title("Upper Bridge Lot"));
            mMap.addMarker(new MarkerOptions().position(cas).title("CAS Lot"));
            mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
            mMap.addMarker(new MarkerOptions().position(fiveseventyfive).title("575 Commonwealth Avenue"));
            mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
            mMap.addMarker(new MarkerOptions().position(kenmore).title("Kenmore Lot"));
            mMap.addMarker(new MarkerOptions().position(seventhirtysevenfifty).title("730/750 Commonwealth Avenue"));
            mMap.addMarker(new MarkerOptions().position(sevensixtysix).title("766 Commonwealth Avenue"));
            mMap.addMarker(new MarkerOptions().position(eightninety).title("890 Commonwealth Avenue"));
        }
    }
    public void employeeCarpool(){
        mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
        mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
        mMap.addMarker(new MarkerOptions().position(cas).title("CAS Lot"));
        mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
        mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
    }
    public void studentFlex(LocalTime time, int day){
        mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
        mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
        if (day > 5 || time.isAfter(LocalTime.of(15,0,0))) {
            // weekends or after 3pm
            mMap.addMarker(new MarkerOptions().position(cas).title("CAS Lot"));
            mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
            mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
        }

    }
    public void studentCommuter(LocalTime time, int day){
        mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
        mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
        if (day > 5 || time.isAfter(LocalTime.of(15,0,0))) {
            // weekends or after 3pm
            mMap.addMarker(new MarkerOptions().position(buick).title("Buick Street Lot"));
            mMap.addMarker(new MarkerOptions().position(cfa).title("CFA Lot"));
            mMap.addMarker(new MarkerOptions().position(lowerbridge).title("Lower Bridge Lot"));
            mMap.addMarker(new MarkerOptions().position(upperbridge).title("Upper Bridge Lot"));
            mMap.addMarker(new MarkerOptions().position(cas).title("CAS Lot"));
            mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
            mMap.addMarker(new MarkerOptions().position(fiveseventyfive).title("575 Commonwealth Avenue"));
            mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
            mMap.addMarker(new MarkerOptions().position(seventhirtysevenfifty).title("730/750 Commonwealth Avenue"));
        }


    }
    public void studentEvening(LocalTime time, int day){
        if (day > 5 || time.isAfter(LocalTime.of(15,0,0))) {
            // weekends or after 3pm
            mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
            mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
            mMap.addMarker(new MarkerOptions().position(buick).title("Buick Street Lot"));
            mMap.addMarker(new MarkerOptions().position(cfa).title("CFA Lot"));
            mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
            mMap.addMarker(new MarkerOptions().position(lowerbridge).title("Lower Bridge Lot"));
            mMap.addMarker(new MarkerOptions().position(upperbridge).title("Upper Bridge Lot"));
            mMap.addMarker(new MarkerOptions().position(cas).title("CAS Lot"));
            mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
            mMap.addMarker(new MarkerOptions().position(fiveseventyfive).title("575 Commonwealth Avenue"));
            mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
            mMap.addMarker(new MarkerOptions().position(kenmore).title("Kenmore Lot"));
            mMap.addMarker(new MarkerOptions().position(seventhirtysevenfifty).title("730/750 Commonwealth Avenue"));
            mMap.addMarker(new MarkerOptions().position(sevensixtysix).title("766 Commonwealth Avenue"));
            mMap.addMarker(new MarkerOptions().position(eightninety).title("890 Commonwealth Avenue"));
        }
    }
    public void studentLangsam(){
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
    }
    public void studentOrange(){
        mMap.addMarker(new MarkerOptions().position(orange1).title("Street Parking"));
        mMap.addMarker(new MarkerOptions().position(orange2).title("Street Parking"));
        mMap.addMarker(new MarkerOptions().position(orange3).title("Street Parking"));
        mMap.addMarker(new MarkerOptions().position(orange4).title("Street Parking"));
    }
    public void guestAgganis(){
        mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
        mMap.addMarker(new MarkerOptions().position(buick).title("Buick Street Lot"));
        mMap.addMarker(new MarkerOptions().position(cfa).title("CFA Lot"));
        mMap.addMarker(new MarkerOptions().position(essex).title("Essex Street Garage & Lot"));
    }
    public void guestRedSox(){
        mMap.addMarker(new MarkerOptions().position(warren).title("Warren Towers Garage"));
        mMap.addMarker(new MarkerOptions().position(fiveseventyfive).title("575 Commonwealth Avenue"));
        mMap.addMarker(new MarkerOptions().position(rafik).title("Rafik B. Hariri Building Garage"));
        mMap.addMarker(new MarkerOptions().position(kenmore).title("Kenmore Lot"));
        mMap.addMarker(new MarkerOptions().position(granby).title("Granby Lot"));
        mMap.addMarker(new MarkerOptions().position(sevensixtysix).title("766 Commonwealth Avenue"));
    }
    public void guestNonEvent(){
        mMap.addMarker(new MarkerOptions().position(agganis).title("Agganis Arena"));
        mMap.addMarker(new MarkerOptions().position(langsam).title("Langsam Garage"));
        mMap.addMarker(new MarkerOptions().position(kenmore).title("Kenmore Lot"));
    }
    /* END OF HELPER FUNCTIONS TO DISPLAY MARKERS*/
}