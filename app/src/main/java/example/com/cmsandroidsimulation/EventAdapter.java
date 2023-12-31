package example.com.cmsandroidsimulation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import example.com.cmsandroidsimulation.datastructures.EventInfo;
import example.com.cmsandroidsimulation.apiwrapper.User;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<EventInfo> eventList;
    private Context context;

    public EventAdapter(ArrayList<EventInfo> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventInfo event = eventList.get(position);

        holder.titleTextView.setText(event.getTitle());
        holder.contentTextView.setText(event.getDetails());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("selectedEventIndex", holder.getAdapterPosition());

                if(User.IsAdminInstant())
                {
                    Navigation.findNavController((View) v.getParent()).
                            navigate(R.id.eventAdminFragment, bundle);
                }
                else {
                    Navigation.findNavController((View) v.getParent()).
                            navigate(R.id.eventFragment, bundle);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorTextView;
        TextView contentTextView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.event_title);
            authorTextView = itemView.findViewById(R.id.event_author);
            contentTextView = itemView.findViewById(R.id.event_content);
        }
    }
}
