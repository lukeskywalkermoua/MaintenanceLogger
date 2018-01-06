package skywalkerapps.maintenancelogger;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Main page of the maintenance app
 * allows users to view created vehicles
 * Created by Luke Moua on 1/1/2018.
 */

public class MainPaige extends AppCompatActivity {

    private final int IMAGE_GALLERY_REQUEST = 20;

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

        //Create subclass custom adapter that modifies the list view
        CustomAdapter myCustomAdapter = new CustomAdapter();
        //Call method to customize the list view
        //setAdapter() is a list view method
        myListView.setAdapter(myCustomAdapter);


        //DatabaseReference instance inherits listener methods that can be called upon
        myDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            //When a new child is created, this code runs
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);

                if (value.matches("")) {
                    //If the nick name is empty/null do nothing
                } else {
                    //If a valid nick name is entered, add it to the array list
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

        //Whenever the user clicks a vehicle on the list, code inside here runs
        //TODO transition to a page where the user can see information about this vehicle
        //TODO whenever the vehicle name in the list is clicked
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainPaige.this, "You clicked a vehicle", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Method to get image from user's phone
    //Linked to onClick from List View
    public void onImageGalleryClicked(View v) {
        //Invoke the image gallery using an implicit intent
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);


        //where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();

        //finaly get URI representation
        Uri data = Uri.parse(pictureDirectoryPath);
        //set the data and type, get all image types using asterisk, jpg, png, gif etc.
        photoPickerIntent.setDataAndType(data, "image/*");

        //we will invoke this activity, and get something back from it
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    //Create a BaseAdapter subclass that will synchronize the customlayout.xml display
    //Everything in this customlayout.xml file will show in the list
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myVehiclesArrayList.size();
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
            view = getLayoutInflater().inflate(R.layout.customlayout, null);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name1 = view.findViewById(R.id.textView2);
            TextView textView_name2 = view.findViewById(R.id.textView3);

            //After the array list of nick-names has been stored in the array, get the values of
            //the array list through inherited BaseAdapter and set them to the text of the text list
                textView_name1.setText(myVehiclesArrayList.get(i));
                textView_name2.setText(myVehiclesArrayList.get(i));
            //Return the view of customlayout.xml file that will
            //be used for the listview in main_paige.xml
            return view;
        }
    }


}