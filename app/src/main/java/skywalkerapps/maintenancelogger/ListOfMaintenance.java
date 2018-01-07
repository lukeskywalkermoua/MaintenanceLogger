package skywalkerapps.maintenancelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This java class will implement the functionality
 * of the maintenance list display of each individual vehicle
 *
 * Created by Luke Moua
 * 1/7/2018.
 */

public class ListOfMaintenance extends AppCompatActivity {

    MainPaigeExtension myVehicleMainPaige= new MainPaigeExtension();

    //Declare DatabaseReference instances to retrieve info. saved
    //to real time data base
    //private DatabaseReference vehicleNicknameDataRef;
    //private DatabaseReference vehicleMakeDataRef;
   // private DatabaseReference vehicleModelDataRef;
    //private DatabaseReference vehicleYearDataRef;

    //Run this code when activity starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the java class to implement list_of_maintenance.xml
        setContentView(R.layout.list_of_maintenance);

        //Testing, return Blackey
        String myString = myVehicleMainPaige.getArrayListNickname(4);
        Toast.makeText(this, "The nickname is " + myString, Toast.LENGTH_LONG).show();

        /**
        //Make sure the pointer of the data references are at the right data location
        vehicleNicknameDataRef = FirebaseDatabase.getInstance().getReference().child("Nickname");
        vehicleMakeDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Make");
        vehicleModelDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Model");
        vehicleYearDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Year");


        vehicleNicknameDataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String nicknameString = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
         **/






    }
}
