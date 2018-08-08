package csi3770.samcalendar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    private ArrayList<String> mKeys = new ArrayList<>();
    private CalendarView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mEventList = (ListView) findViewById(R.id.event_list);
        events = new EventManager();

        final EventsAdapter eventsAdapter = new EventsAdapter(this, events.getEvents());
        mEventList.setAdapter(eventsAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String tDate = (String)dataSnapshot.child("date").getValue();
                String tLocation = (String)dataSnapshot.child("location").getValue();
                String tDetails = (String)dataSnapshot.child("details").getValue();
                String tKey = dataSnapshot.getKey();

                Event tEvent = new Event(tDate,tLocation,tDetails,tKey);

                events.addEvent(tEvent);
                events.getEventDates().add(tEvent.getDateAsDate());

                mKeys.add(dataSnapshot.child("date").getKey());
                mKeys.add(dataSnapshot.child("location").getKey());
                mKeys.add(dataSnapshot.child("details").getKey());

                eventsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                String date = (String) dataSnapshot.child("date").getValue();
                String location = (String) dataSnapshot.child("location").getValue();
                String details = (String) dataSnapshot.child("details").getValue();

                Event updateEvent = events.getEvent(key);
                updateEvent.setDate(date);
                updateEvent.setLocation(location);
                updateEvent.setDetails(details);

                eventsAdapter.notifyDataSetChanged();
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

        cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events.getEventDates());

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
                startActivityForResult(intent, 999);
            }

            @Override
            public void onShortPress(Date date){

                Toast.makeText(MainActivity.this, "display events for this day", Toast.LENGTH_SHORT).show();
                cv.updateCalendar(events.getEventDates());

            }
        });

        // when an item is clicked we want to update it.
        mEventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Event selected_event = (Event)adapterView.getAdapter().getItem(i);

                String selectedFromList2 = "Key: " + selected_event.getKey();

                Toast.makeText(MainActivity.this, selectedFromList2, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        cv.updateCalendar(events.getEventDates());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 999 && resultCode == Activity.RESULT_OK){
//            String user_event_data = "date: " + data.getStringExtra("date") +
//                    ", location: " + data.getStringExtra("location") +
//                    ", details: " + data.getStringExtra("details");
//            Toast.makeText(MainActivity.this, user_event_data , Toast.LENGTH_SHORT).show();
            Event event = new Event(data.getStringExtra("date"),
                    data.getStringExtra("location"),
                    data.getStringExtra("details"));

            mDatabase.push().setValue(event);
        }
    }
}
