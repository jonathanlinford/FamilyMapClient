package jonathanlinford.familymapclient.Activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;

import jonathanlinford.familymapclient.DataTransfer.InternalData;
import jonathanlinford.familymapclient.R;

public class SettingsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        for(String eventType : InternalData.colors.keySet()){
            //((MyAdapter) adapter).add
        }
    }
}
