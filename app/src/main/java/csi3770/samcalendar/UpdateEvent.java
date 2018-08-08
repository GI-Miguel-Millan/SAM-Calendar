package csi3770.samcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateEvent extends AppCompatActivity {
    private Date date;
    private String location = "";
    private String details = "";
    private String key = "";

    private TextView tvDate;
    private EditText etLocation;
    private EditText etDetails;
    private Button btnUpdateEvent;
    private Button btnRemoveEvent;

    private final int RESULT_REMOVE_EVENT = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        tvDate = findViewById(R.id.DateSelected); // grab instance of date selected textview
        etLocation = findViewById(R.id.EditLocation); // grab instance of location editText
        etDetails = findViewById(R.id.EditDetails); // grab instance of Details editText
        btnUpdateEvent = findViewById(R.id.ButtonUpdateEvent); // grab instance of button
        btnRemoveEvent = findViewById(R.id.ButtonRemoveEvent);

        // Get the data sent from MainActivity
        date = new Date();
        Bundle extras = getIntent().getExtras();
        if (extras !=null) {
            date.setTime(extras.getLong("date"));
            location = extras.getString("location");
            details = extras.getString("details");
            key = extras.getString("key");
        }

        // Get the set the date to the event label
        DateFormat df = SimpleDateFormat.getDateInstance();
        final String date_to_string = df.format(date);
        tvDate.setText("Event on: " + date_to_string);

        etLocation.setText(location); // set previous location
        etDetails.setText(details); // set previous details

        btnUpdateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc = etLocation.getText().toString();
                String det = etDetails.getText().toString();

                Intent newEvent = new Intent();
                newEvent.putExtra("date", date_to_string);
                newEvent.putExtra("location", loc);
                newEvent.putExtra("details", det);
                newEvent.putExtra("key", key);
                setResult(RESULT_OK, newEvent);
                finish();
            }
        });

        btnRemoveEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent newEvent = new Intent();
                newEvent.putExtra("key", key);
                setResult(RESULT_REMOVE_EVENT, newEvent);
                finish();
            }
        });



    }
}
