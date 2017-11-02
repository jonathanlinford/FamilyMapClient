package jonathanlinford.familymapclient.Activities.Filter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import jonathanlinford.familymapclient.Activities.MainActivity;
import jonathanlinford.familymapclient.Fragments.MapFragment;
import jonathanlinford.familymapclient.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<FilterItem> lines = Collections.emptyList();

    public List<FilterItem> getLines() {
        return lines;
    }

    public void setLines(List<FilterItem> lines) {
        this.lines = lines;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_title_switch_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FilterItem current = lines.get(position);

        holder.mTextView.setText(current.text);
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public Switch mSwitch;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.event_text);
            mSwitch = (Switch) v.findViewById(R.id.event_switch);

            mSwitch.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    String type;
                    String param;
                    switch(mTextView.getText().toString().toLowerCase()) {
                        case "male" :
                            type = "gender";
                            param = "m";
                            break;
                        case "female" :
                            type = "gender";
                            param = "f";
                            break;
                        case "father" :
                            type = "parent";
                            param = "father";
                            break;
                        case "mother" :
                            type = "parent";
                            param = "mother";
                            break;
                        default :
                            type = "eventType";
                            param = mTextView.getText().toString();

                    }
                    if(mSwitch.isChecked()){
                        MainActivity.mapFragment.updateMap(type, null, param);
                        mSwitch.setChecked(true);
                    } else{
                        MainActivity.mapFragment.updateMap(type, param, null);
                        mSwitch.setChecked(false);
                    }
                }

            });


        }
    }
}
