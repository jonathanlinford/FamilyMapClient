package jonathanlinford.familymapclient.Activities.Person;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.games.event.Events;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jonathanlinford.familymapclient.Activities.Filter.FilterItem;
import jonathanlinford.familymapclient.Activities.MainActivity;
import jonathanlinford.familymapclient.Model.*;
import jonathanlinford.familymapclient.R;

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.ViewHolder> {
    private List<Event> events;

    public void setData(Event[] events) {
        this.events = Arrays.asList(events);


    }

    @Override
    public MyEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child, parent, false);

        MyEventAdapter.ViewHolder viewHolder = new MyEventAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyEventAdapter.ViewHolder holder, int position) {
        Event current = events.get(position);

        holder.mTextView.setText(current.getEventType() + ": " +
                current.getCity() + ", " +
                current.getCountry() + " (" +
                current.getYear() + ")");

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.child_text);
        }
    }
}
