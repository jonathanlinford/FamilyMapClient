package jonathanlinford.familymapclient.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.Map;

import jonathanlinford.familymapclient.Activities.Person.PersonActivity;
import jonathanlinford.familymapclient.Activities.SettingsActivity;
import jonathanlinford.familymapclient.DataTransfer.InternalData;
import jonathanlinford.familymapclient.Model.*;
import jonathanlinford.familymapclient.R;

public class MapFragment extends SupportMapFragment implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback{

    public static GoogleMap googleMap;
    private MapView mapView;
    private LayoutInflater layoutInflater;
    private MenuItem menuItem;
    private Menu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){

        this.layoutInflater = layoutInflater;
        super.onCreateView(layoutInflater, viewGroup, savedInstanceState);

        View v = layoutInflater.inflate(R.layout.fragment_map,	viewGroup,	false);

        mapView = (MapView) v.findViewById(R.id.mapview);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        try {
            for (Event event : InternalData.events) {
                if (!InternalData.colors.containsKey(event.getEventType().toLowerCase()))
                    updateColors(event.getEventType());

                Person person = InternalData.searchPersons("personID", event.getPersonID())[0];

                LatLng loc = new LatLng(event.getLatitude(), event.getLongitude());

                //Creation of the marker and concatenation of the snippet
                Marker marker = googleMap.addMarker(new MarkerOptions().position(loc)
                        .title(event.getEventID())
                        .icon(BitmapDescriptorFactory.defaultMarker(InternalData.colors.get(event.getEventType().toLowerCase()))));

                InternalData.eventMarkerMap.put(event, marker);
            }

            setUpAdapterView();

        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent;

        intent = new Intent(this.getContext(), PersonActivity.class);
        intent.putExtra("personID", InternalData.keyFromValue(marker).getPersonID());
        startActivity(intent);
    }

    private boolean updateColors(String s){
            InternalData.colors.put(s.toLowerCase(), new Float(0));

            assert (InternalData.colors.size() <= 360 && InternalData.colors.size() >= 0);

            int distance = 359 / InternalData.colors.size();
            int distanceIterator = distance;

            for (String key : InternalData.colors.keySet()) {
                InternalData.colors.put(key, new Float(distanceIterator));
                distanceIterator += distance;
            }

        return true;
    }

    public void updateMap(String type, String noMore, String addBack){
        Map<Event, Marker> events;
        googleMap.clear();

        String param = (noMore != null) ? noMore : addBack;

        events = InternalData.searchEvents(type, param);

        if(noMore != null) {
            for(Event e : events.keySet()){
                events.get(e).setVisible(false);
            }
        }else{
            for(Event e : events.keySet()){
                events.get(e).setVisible(true);
            }
        }

        InternalData.eventMarkerMap.putAll(events);
        for(Marker m : InternalData.eventMarkerMap.values()){
            googleMap.addMarker(new MarkerOptions().position(m.getPosition())
                    .visible(m.isVisible())
                    .title(m.getTitle())
                    .icon(BitmapDescriptorFactory.defaultMarker(InternalData.colors.get(InternalData.keyFromValue(m).getEventType()))));

            setUpAdapterView();
        }

    }

    public void setUpAdapterView(){
        // Setting a custom info window adapter for the google map
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {
                Event event = InternalData.keyFromValue(arg0);
                Person person = InternalData.searchPersons("personID", event.getPersonID())[0];


                // Getting view from the layout file info_window_layout
                View v = layoutInflater.inflate(R.layout.info_window_layout, null);

                // Getting reference to the TextView to set latitude
                TextView nameAndGender = (TextView) v.findViewById(R.id.name_gender);

                // Getting reference to the TextView to set longitude
                TextView eventInfo = (TextView) v.findViewById(R.id.event_info);

                // Setting the latitude
                nameAndGender.setText(person.getFirstName() + " " +
                        person.getLastName() + " - " +
                        cap(Character.toString(person.getGender())));

                // Setting the longitude
                eventInfo.setText(cap(event.getEventType()) + ": " +
                        cap(event.getCity()) + ", " +
                        cap(event.getCountry()) + " (" +
                        event.getYear() + ")");

                // Returning the view containing InfoWindow contents
                return v;

            }
        });

        googleMap.setOnInfoWindowClickListener(this);
    }

    public static String cap(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
