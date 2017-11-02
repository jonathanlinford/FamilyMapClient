package jonathanlinford.familymapclient.Activities.Person;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jonathanlinford.familymapclient.Activities.Filter.*;
import jonathanlinford.familymapclient.DataTransfer.InternalData;
import jonathanlinford.familymapclient.Model.*;
import jonathanlinford.familymapclient.R;

public class PersonActivity extends AppCompatActivity {

    private RecyclerView eventRecyclerView;
    private RecyclerView familyRecyclerView;
    private MyEventAdapter eventAdapter;
    private MyPersonAdapter personAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView firstName;
    private TextView lastName;
    private TextView gender;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        String personID = getIntent().getStringExtra("personID");
        Person person = InternalData.searchPersons("personID", personID)[0];
        Person father = (InternalData.searchPersons("personID", person.getFather()) != null) ? InternalData.searchPersons("personID", person.getFather())[0] : null;
        Person mother = (InternalData.searchPersons("personID", person.getMother()) != null) ? InternalData.searchPersons("personID", person.getMother())[0] : null;

        Person[] persons = {father, mother};

        Map<Event, Marker> eventsMap = InternalData.searchEvents("personID", personID);
        Event[] events = eventsMap.keySet().toArray(new Event[eventsMap.size()]);

        firstName = (TextView) findViewById(R.id.first_name);
        lastName = (TextView) findViewById(R.id.last_name);
        gender = (TextView) findViewById(R.id.gender);

        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        gender.setText("" + person.getGender());

        eventRecyclerView = (RecyclerView) findViewById(R.id.event_recyclerView);
        familyRecyclerView = (RecyclerView) findViewById(R.id.family_recyclerView);

        eventAdapter = new MyEventAdapter();
        personAdapter = new MyPersonAdapter();

        eventAdapter.setData(events);
        personAdapter.setData(persons);


        mLayoutManager = new LinearLayoutManager(this);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventRecyclerView.setAdapter(eventAdapter);
        familyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        familyRecyclerView.setAdapter(personAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
