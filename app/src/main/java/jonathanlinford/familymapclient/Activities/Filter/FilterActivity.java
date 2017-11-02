package jonathanlinford.familymapclient.Activities.Filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jonathanlinford.familymapclient.DataTransfer.InternalData;
import jonathanlinford.familymapclient.Fragments.MapFragment;
import jonathanlinford.familymapclient.Model.Event;
import jonathanlinford.familymapclient.R;

public class FilterActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        recyclerView = (RecyclerView) findViewById(R.id.filter_recycler_view);

        mAdapter = new MyAdapter();
        mAdapter.setLines(getFilterItems());

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public static List<FilterItem> getFilterItems(){
        ArrayList<FilterItem> lines = new ArrayList<>();
        lines.add(new FilterItem("father"));
        lines.add(new FilterItem("mother"));
        lines.add(new FilterItem("male"));
        lines.add(new FilterItem("female"));

        for(String eventType : InternalData.colors.keySet()){
            lines.add(new FilterItem(eventType));
        }

        return lines;
    }
}
