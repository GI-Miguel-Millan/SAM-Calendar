package csi3770.samcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEvent extends AppCompatActivity {
    private Date date;
    private TextView tvDate;
    private EditText etLocation;
    private EditText etDetails;
    private Button btnAddEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        tvDate = findViewById(R.id.DateSelected); // grab instance of date selected textview
        etLocation = findViewById(R.id.EditLocation); // grab instance of location editText
        etDetails = findViewById(R.id.EditDetails); // grab instance of Details editText
        btnAddEvent = findViewById(R.id.ButtonAddEvent); // grab instance of button

        // Get the date sent from MainActivity
        date = new Date();
        Bundle extras = getIntent().getExtras();
        if (extras !=null) {
            date.setTime(extras.getLong("date"));
        }

        // Get the set the date to the event label
        DateFormat df = SimpleDateFormat.getDateInstance();
        final String date_to_string = df.format(date);
        tvDate.setText("Event on: " + date_to_string);

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc = etLocation.getText().toString();
                String det = etDetails.getText().toString();

                String user_event_data = "date: " + date_to_string +
                        ", location: " + loc +
                        ", details: " + det;

                //Toast.makeText(AddEvent.this, user_event_data , Toast.LENGTH_SHORT).show();

                Intent newEvent = new Intent();
                newEvent.putExtra("date", date_to_string);
                newEvent.putExtra("location", loc);
                newEvent.putExtra("details", det);
                setResult(RESULT_OK, newEvent);
                finish();
            }
        });



    }
}
