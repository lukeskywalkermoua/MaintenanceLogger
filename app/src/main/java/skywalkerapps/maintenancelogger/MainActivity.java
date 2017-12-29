package skywalkerapps.maintenancelogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Create button
    private Button myButton;

    //Create a database instance
    private DatabaseReference myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.my_button1);

        //Point my data base instance to the direct root of the data app
        myDatabase = FirebaseDatabase.getInstance().getReference();

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When the button is clicked, the real time data is updated

                //Create child in root object

                //Assign some value to the child object

                myDatabase.child("Name").setValue("Luke M");


            }
        });




    }
}
