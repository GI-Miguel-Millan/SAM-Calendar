package csi3770.samcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class AddEvent extends AppCompatActivity {
    private Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        date = new Date();
        Bundle extras = getIntent().getExtras();
        if (extras !=null) {
            date.setTime(extras.getLong("date"));
        }

        setContentView(R.layout.activity_add_event);
    }
}
