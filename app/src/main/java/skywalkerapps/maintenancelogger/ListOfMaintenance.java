package skywalkerapps.maintenancelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This java class will implement the functionality
 * of the maintenance list display of each individual vehicle
 *
 * Created by Luke Moua
 * 1/7/2018.
 */

public class ListOfMaintenance extends AppCompatActivity {

    private ArrayList<String> maintenanceTypeArrayList = new ArrayList<>();

    //Array list of default images for the vehicle maintenance list
    private int[] maintenancePicArray = new int[] {R.drawable.car_battery, R.drawable.brakes, R.drawable.oil_drop
            , R.drawable.spark_plug, R.drawable.tires, R.drawable.tool};

    private void addDefaultListItems() {
        final String BATTERY = "Battery";
        final String BRAKE_JOB = "Brake Job";
        final String OIL_CHANGE = "Oil Change";
        final String SPARK_PLUGS = "Spark Plugs";
        final String TIRE_JOB = "Tires";
        
        //Add default categories to the vehicle maintenance list
        maintenanceTypeArrayList.add(BATTERY);
        maintenanceTypeArrayList.add(BRAKE_JOB);
        maintenanceTypeArrayList.add(OIL_CHANGE);
        maintenanceTypeArrayList.add(SPARK_PLUGS);
        maintenanceTypeArrayList.add(TIRE_JOB);
    }


    //Run this code when activity starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the java class to implement list_of_maintenance.xml
        setContentView(R.layout.list_of_maintenance);
        //Declare list view instance and initialize it
        ListView maintenanceList = findViewById(R.id.listView);

        //Add default categories to the vehicle maintenance list
        addDefaultListItems();

        CustomAdapterTwo myCustomAdapter = new CustomAdapterTwo();

        maintenanceList.setAdapter(myCustomAdapter);


    }

    //Custom adapter sub class for implementing the look of the list view
    class CustomAdapterTwo extends BaseAdapter {
        @Override
        public int getCount() {
            return maintenanceTypeArrayList.size();
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
            view = getLayoutInflater().inflate(R.layout.customlayoutmaintenancelist, null);

            TextView maintenanceJobText = view.findViewById(R.id.textViewMaintenance4);

            ImageView maintenanceImageView = view.findViewById(R.id.imageView3);

            maintenanceJobText.setText(maintenanceTypeArrayList.get(i));

            maintenanceImageView.setImageResource(maintenancePicArray[i]);

            return view;
        }

    }
    }
