package skywalkerapps.maintenancelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

/**
 * Main page of the maintenance app
 * allows users
 * Created by Luke Moua on 1/1/2018.
 */

public class MainPaige extends AppCompatActivity{
    //Declare instances
    //Create a ListView instance
    private ListView myListView;

    //Create a FirebaseDatabase instance
    private DatabaseReference myDatabase;

    //Create an array list that will grow when more
    //vehicles are added to it
    private ArrayList<String> myVehiclesArrayList = new ArrayList<>();

    //Run this code when the acitivity starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set class to implement main_page.xml
        setContentView(R.layout.main_page);

        //Point the data base instance to the real time database online
        myDatabase = FirebaseDatabase.getInstance().getReference();

        //Setup the id of the list view according to xml list view
        myListView = findViewById(R.id.vehicle_list1);

        //Create an array adapter to sync with the array list
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myVehiclesArrayList);
        //array list "myList" uses setAdapter method to sync with arrayAdapter
        myListView.setAdapter(arrayAdapter);

        //DatabaseReference instance inherits listener methods that can be called upon
        myDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            //When a new child is created, this code runs
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                //Add the new string to the array list
                if(value.matches("")) {
                } else {
                    myVehiclesArrayList.add(value);
                    //Update the data when it changes
                    arrayAdapter.notifyDataSetChanged();
                }
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





    }

}
