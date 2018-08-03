package csi3770.samcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity
{
    private DatabaseReference mDatabase;
    private ListView mEventList;
    private EventManager events;
    private ArrayList<String> event_info = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("events").child("test-id");
        mEventList = (ListView) findViewById(R.id.event_list);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, event_info);
        mEventList.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                event_info.add(value);

                String key = dataSnapshot.getKey();
                mKeys.add(key);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();

                int index = mKeys.indexOf(key);

                event_info.set(index,value);

                arrayAdapter.notifyDataSetChanged();
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

        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler()
        {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day
//                DateFormat df = SimpleDateFormat.getDateInstance();
//                Toast.makeText(MainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, "create event on long press", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getBaseContext(), AddEvent.class);
                intent.putExtra("date", date.getTime());
                startActivity(intent);
            }

            @Override
            public void onShortPress(Date date){
                // show returned day
                //DateFormat df = SimpleDateFormat.getDateInstance();
                //Toast.makeText(MainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "display events for this day", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
