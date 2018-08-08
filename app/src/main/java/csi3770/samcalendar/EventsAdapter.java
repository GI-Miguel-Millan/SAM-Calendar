package csi3770.samcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventsAdapter extends ArrayAdapter<Event>{
    public EventsAdapter(Context context, ArrayList<Event> events){
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }
        // Lookup view for data population
        TextView date = (TextView) convertView.findViewById(R.id.list_date);
        TextView location = (TextView) convertView.findViewById(R.id.list_location);
        TextView details = (TextView) convertView.findViewById(R.id.list_details);
        // Populate the data into the template view using the data object
        date.setText("Date: " + event.getDate());
        location.setText("Location: " + event.getLocation());
        details.setText("Details:\n" + event.getDetails());
        // Return the completed view to render on screen
        return convertView;
    }
}
