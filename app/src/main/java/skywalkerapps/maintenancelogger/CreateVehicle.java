package skywalkerapps.maintenancelogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Main class that saves user input such as
 * logging new vehicles/updating vehicle info.
 * and storing it to the real time data Firebase
 *
 * Creator: Luke Moua
 * Company: Skywalker Apps
 * Date: 01/01/2018
 */

public class CreateVehicle extends AppCompatActivity {

    //Create a database instance
    private DatabaseReference vehicleMakeDataRef;
    private DatabaseReference vehicleModelDataRef;
    private DatabaseReference vehicleYearDataRef;
    private DatabaseReference vehicleOtherDataRef;
    private DatabaseReference vehicleNickNameDataRef;

    //Displays short toast message to notify that input cannot be left blank
    private void makeToast(String myString) {
        Toast.makeText(this, myString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create button variables for each category, make, model, year etc.
        final Button vehicleSaveButton;

        final Button viewMyListButton;


        //Create an edit text variable for each category
        //TODO Future update could be to include list of existing vehicles/equipment
        //and then just allow user to pick from existing options
        final EditText vehicleMakeEditText;
        final EditText vehicleModelEditText;
        final EditText vehicleYearEditText;
        final EditText vehicleOtherDescEditText;
        final EditText vehicleNickNameEditText;

        super.onCreate(savedInstanceState);
        //Code implements activity_createvehicle.xml file
        setContentView(R.layout.activity_createvehicle);

        //Set the id of save button in xml to the Button variable
        vehicleSaveButton = findViewById(R.id.save_button);
        //Set id of transition activity button in xml to Button variable
        viewMyListButton = findViewById(R.id.button2);

        //Edit text box for nick name
        vehicleNickNameEditText = findViewById(R.id.editText0);
        //Edit text box for brand
        vehicleMakeEditText = findViewById(R.id.editText1);
        //Edit text box for the vehicle brand
        vehicleModelEditText = findViewById(R.id.editText2);
        //Edit text box for the year
        vehicleYearEditText = findViewById(R.id.editText3);
        //Edit textbox for other vehicle specs.
        vehicleOtherDescEditText = findViewById(R.id.editText4);

        //Point my data base instance to the direct root of the data app
        //Sets the reference category file to save the data in make/model/Year child data save

        //Saves the vehicle nick name under main database default reference
        vehicleNickNameDataRef = FirebaseDatabase.getInstance().getReference().child("Nickname");
        //Saves Vehicle Make(brand) under Make
        vehicleMakeDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Make");

        //Saves Vehicle Model under Make/Model
        vehicleModelDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Model");

        //Saves Vehicle year under Make/Model/Year
        vehicleYearDataRef = FirebaseDatabase.getInstance().getReference().child("Vehicle Year");

        //Saves the custom vehicle description under Make/Model/Year/Other
        vehicleOtherDataRef = FirebaseDatabase.getInstance().getReference().child("Other Descriptions");

        //Button Listener that allows switches to the next activity to
        //view existing registered vehicles
        viewMyListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Switches from CreateVehicle activity to MainPaige activity
                Intent myIntent = new Intent(CreateVehicle.this, MainPaige.class);
                startActivity(myIntent);
            }
        });

        //Button save listener for vehicle make brand) Toyota, Ford, Chevy, etc..
        vehicleSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //When the button is clicked, the real time data is updated
                //Create child in root object
                //Assign some value to the child object
                //Retrieve the input text from the user and store
                //it in a string variable
                String stringVehicleMake = vehicleMakeEditText.getText().toString().trim();

                //TESTINGvehicleMakeDataRef.push().setValue(stringVehicleMake);

                //Gets the vehicle model from user and stores it in a string
                String stringVehicleModel = vehicleModelEditText.getText().toString().trim();

                //TESTINGvehicleModelDataRef.push().setValue(stringVehicleModel);

                //Gets the vehicle year from user and stores it in a string
                String stringVehicleYear = vehicleYearEditText.getText().toString().trim();

                //TESTINGvehicleYearDataRef.push().setValue(stringVehicleYear);

                //Gets other descriptions from user and stores it in a string
                String stringVehicleOther = vehicleOtherDescEditText.getText().toString().trim();
                //Saves the string of Other desc. under Other Descriptions

                //TESTINGvehicleOtherDataRef.push().setValue(stringVehicleOther);

                //Gets nickname from user and stores it in string variable
                String stringVehicleNickName = vehicleNickNameEditText.getText().toString().trim();
                //Saves the string of nickname under Vehicle Nickname
                vehicleNickNameDataRef.push().setValue(stringVehicleNickName);


                    //Uses boolean string method matches() to check if the
                    //make, model, and year inputs are empty, and if do loop until
                    //user makes necessary changes

                    if (stringVehicleMake.matches("")) {
                        makeToast("WARNING vehicle make is left empty");
                    } else {
                        //Sets the name of the child to the string when the button is clicked
                        //push() method enables a new child to be created each time button
                        //is clicked (does not overwrite)
                        //@Params string of vehicle make/model/year
                        vehicleMakeDataRef.push().setValue(stringVehicleMake);
                    }
                    if (stringVehicleModel.matches("")) {
                        makeToast("WARNING vehicle model is left empty");
                    } else {
                        //Saves the string of vehicle model under the Vehicle Model Reference
                        vehicleModelDataRef.push().setValue(stringVehicleModel);
                    }
                    if (stringVehicleYear.matches("")) {
                        makeToast("WARNING vehicle year is left empty");
                    } else {
                        //Saves the string of vehicle year under Vehicle Year reference
                        vehicleYearDataRef.push().setValue(stringVehicleYear);
                    }
                    //If user decides not to use nickname for vehicle, give hint toast message and
                    //notify that info. is saved. Else, notify that vehicle with nickname _______ is saved...
                    if (stringVehicleNickName.matches("")) {
                        makeToast("You can always change vehicle info. later");
                        makeToast("Created " + stringVehicleYear + " " + stringVehicleMake + " " + stringVehicleModel);
                    } else {
                        makeToast("Created " + stringVehicleNickName);
                    }
                    if(stringVehicleOther.matches("")) {
                        vehicleOtherDataRef.push().setValue("Other data is empty....");
                    } else {
                        vehicleOtherDataRef.push().setValue(stringVehicleOther);
                    }
            }
        });

        finish();


    }
}
