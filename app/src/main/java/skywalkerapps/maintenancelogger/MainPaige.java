package skywalkerapps.maintenancelogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

/**
 * Main page of the maintenance app
 * allows users to view created vehicles
 * Created by Luke Moua on 1/1/2018.
 */

public class MainPaige extends AppCompatActivity {


    //Create an array list for every data category that will grow when more vehicle info is added to it
    //Protected, so it's only accessible by child class MainPaige Extension
    protected ArrayList<String> nicknameArrayList = new ArrayList<>();
    protected ArrayList<String> makeArrayList = new ArrayList<>();
    protected ArrayList<String> modelArrayList = new ArrayList<>();
    protected ArrayList<String> yearArrayList = new ArrayList<>();

    //Create an array to store the default images of vehicle pictures
    int[] defaultPictureArray = {R.drawable.bmwlogo, R.drawable.chevylogo, R.drawable.dodgelogo, R.drawable.fordlogo, R.drawable.hondalogo
        ,R.drawable.nissanlogo, R.drawable.volkswagenlogo, R.drawable.toyotalogo, R.drawable.lambopic};

    //Run this code when the acitivity starts
    protected void onCreate(Bundle savedInstanceState) {

        ListView myListView;
        DatabaseReference nicknameDatabase;
        DatabaseReference makeDatabase;
        DatabaseReference modelDatabase;
        DatabaseReference yearDatabase;

        super.onCreate(savedInstanceState);
        //Set class to implement main_page.xml
        setContentView(R.layout.main_page);


        //Point the data base instance to the real time database online
        //Make sure that child name matches exactly what data you want to retrieve
        nicknameDatabase = FirebaseDatabase.getInstance().getReference().child("Nickname");
        makeDatabase = FirebaseDatabase.getInstance().getReference().child("Vehicle Make");
        modelDatabase = FirebaseDatabase.getInstance().getReference().child("Vehicle Model");
        yearDatabase = FirebaseDatabase.getInstance().getReference().child("Vehicle Year");

        //Setup the id of the list view according to xml list view
        myListView = findViewById(R.id.vehicle_list1);

        //Create subclass custom adapter that modifies the list view
        CustomAdapter myCustomAdapter = new CustomAdapter();
        //Call method to customize the list view
        //setAdapter() is a list view method
        myListView.setAdapter(myCustomAdapter);

        yearDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String yearString = dataSnapshot.getValue(String.class);
                yearArrayList.add(yearString);
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

        modelDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String modelString = dataSnapshot.getValue(String.class);
                modelArrayList.add(modelString);
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

        makeDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String makeString = dataSnapshot.getValue(String.class);
                makeArrayList.add(makeString);
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


        //DatabaseReference instance inherits listener methods that can be called upon
        nicknameDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            //When a new child is created, this code runs
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String nicknameString = dataSnapshot.getValue(String.class);

                if (nicknameString.matches("")) {
                    //If the nick name is not used, do nothing
                    nicknameArrayList.add("No nickname");
                } else {
                    //If a valid nick name is entered, add it to the array list
                    nicknameArrayList.add(nicknameString);

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

        //Whenever the user clicks a vehicle on the list, code inside here runs
        //TODO transition to a page where the user can see information about this vehicle
        //TODO whenever the vehicle name in the list is clicked
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //When a specific vehicle in the listview is clicked, transition to the List of maintenance
                //page for that specific vehicle
                Intent myIntent = new Intent(MainPaige.this, ListOfMaintenance.class);
                startActivity(myIntent);
            }
        });

    }


    //Create a BaseAdapter subclass that will synchronize the customlayout.xml display
    //Everything in this customlayout.xml file will show in the list
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nicknameArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //Sync the CustomAdapter subclass with customlayout.xml
            view = getLayoutInflater().inflate(R.layout.customlayout, null);

            TextView textView_name1 = view.findViewById(R.id.textView2);
            TextView textView_name2 = view.findViewById(R.id.textView3);

            //After the array list of nick-names has been stored in the array, get the values of
            //the array list through inherited BaseAdapter and set them to the text of the text list
                textView_name1.setText(nicknameArrayList.get(i));

                //Format the vehicle info by retrieving it from the arrays
                String fullSentence = makeArrayList.get(i) + " " + modelArrayList.get(i) + " "
                        + yearArrayList.get(i);
                textView_name2.setText(fullSentence);

            //Sync with the image view from customlayout.xml
            ImageView defaultImage = view.findViewById(R.id.imageView);
            //Generates a picture of the vehicle make on the list view
            //using a switch statement based on user input
            //local int variable that signifies the position inside
            //the array containing the images
            int arrayInt = 0;
            //Use a switch statement to check if the vehicle make entered by
            //the user, matches the picture intended for default use
            switch (makeArrayList.get(i)) {
                case "BMW":
                    arrayInt = 0;
                    break;
                case "Chevrolet":
                    arrayInt = 1;
                    break;
                case "Dodge":
                    arrayInt = 2;
                    break;
                case "Ford":
                    arrayInt = 3;
                    break;
                case "Honda":
                    arrayInt = 4;
                    break;
                case "Nissan":
                    arrayInt = 5;
                    break;
                case "Volkswagen":
                    arrayInt = 6;
                    break;
                case "Toyota":
                    arrayInt = 7;
                    break;
                default:
                    arrayInt = 8;
                    break;
            }

            //Set the image view to the drawable inside the array
            //at position #arrayInt
            defaultImage.setImageResource(defaultPictureArray[arrayInt]);

            //Return the view of customlayout.xml file that will
            //be used for the listview in main_paige.xml
            return view;
        }
    }


}