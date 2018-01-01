package skywalkerapps.maintenancelogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Create a database instance
    private DatabaseReference vehicleMakeDataRef;
    private DatabaseReference vehicleModelDataRef;
    private DatabaseReference vehicleYearDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create button variables for each category, make, model, year etc.
        Button vehicleMakeButton;
        Button vehicleModelButton;
        Button vehicleYearButton;

        //Create an edit text variable for each category
        //TODO Future update could be to include list of already existing vehicles/equipment
        //and then just allow user to pick from existing options
        final EditText vehicleMakeEditText;
        final EditText vehicleModelEditText;
        final EditText vehicleYearEditText;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the id of the button in xml to the Button variable
        vehicleMakeButton = findViewById(R.id.my_button1);

        vehicleModelButton = findViewById(R.id.my_button2);

        vehicleYearButton = findViewById(R.id.my_button3);

        //Set the id of the edit text in xml to the EditText variable
        vehicleMakeEditText = findViewById(R.id.editText1);

        vehicleModelEditText = findViewById(R.id.editText2);

        vehicleYearEditText = findViewById(R.id.editText3);



        //Point my data base instance to the direct root of the data app
        //Sets the reference category file to save the data in make/model/Year child data save
        //The broadest category is the vehicle make(brand), then the model followed by year
        vehicleMakeDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Make");
        vehicleModelDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Make").child("Vehicle Model");
        vehicleYearDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Make").child("Vehicle Model").child("Vehicle Year");

        //Button save listener for vehicle make brand) Toyota, Ford, Chevy, etc..
        vehicleMakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When the button is clicked, the real time data is updated
                //Create child in root object
                //Assign some value to the child object
                //Retrieve the input text from the user and store
                //it in a string variable
                String stringVehicleMake = vehicleMakeEditText.getText().toString().trim();

                //Sets the name of the child to the string when the button is clicked
                //push() method enables a new child to be created each time button
                //is clicked (does not overwrite)
                //@Params string of vehicle make/model/year
                vehicleMakeDataRef.push().setValue(stringVehicleMake);

            }
        });
        //Button save listener for vehicle model
        vehicleModelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Gets the vehicle model from user and stores it in a string
                String stringVehicleModel = vehicleModelEditText.getText().toString().trim();

                vehicleModelDataRef.push().setValue(stringVehicleModel);

            }
        });
        //Button save listener for vehicle year
        vehicleYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gets the vehicle year from user and stores it in a string
                String stringVehicleYear = vehicleYearEditText.getText().toString().trim();
                //Creates a new year for
                vehicleYearDataRef.push().setValue(stringVehicleYear);

            }
        });



    }
}
