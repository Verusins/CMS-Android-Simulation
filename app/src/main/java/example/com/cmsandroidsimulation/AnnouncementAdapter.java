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

import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {

    private ArrayList<Announcement> announcementsList;
    private Context context;

    public AnnouncementAdapter(ArrayList<Announcement> eventList, Context context) {
        this.announcementsList = PlaceholderValues.generateTestAnnouncementList();
        this.context = context;
    }

    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item, parent, false);
        return new AnnouncementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position) {
        Announcement announcement = announcementsList.get(position);

        holder.titleTextView.setText(announcement.getTitle());
        holder.contentTextView.setText(announcement.getDetails());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("selectedEventIndex", holder.getAdapterPosition());
//                Navigation.findNavController((View) v.getParent()).
//                        navigate(R.id.eventFragment, bundle);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return announcementsList.size();
    }

    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView contentTextView;

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.announcement_title_text);
            contentTextView = itemView.findViewById(R.id.announcement_content_text);
            View closeAnnouncementView = itemView.findViewById(R.id.close_announcement);
            closeAnnouncementView.setVisibility(View.GONE);
        }
    }
}
