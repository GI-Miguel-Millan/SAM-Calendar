package csi3770.samcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEvent extends AppCompatActivity {
    private Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        date = new Date();
        Bundle extras = getIntent().getExtras();
        if (extras !=null) {
            date.setTime(extras.getLong("date"));
        }

        TextView tvDate = findViewById(R.id.DateSelected);
        DateFormat df = SimpleDateFormat.getDateInstance();

        tvDate.setText("Event on: " + df.format(date));



    }
}
