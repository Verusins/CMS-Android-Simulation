package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.events.Event;

import java.util.ArrayList;

import example.com.cmsandroidsimulation.databinding.FragmentDashboardStudentBinding;
import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public class DashboardStudentFragment extends Fragment {
    FragmentDashboardStudentBinding binding;
    EventAdapter adapter;
    ArrayList<EventInfo> eventList = PlaceholderValues.generateTestEventInfoList();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDashboardStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout sidebar = binding.sidebarWrapper;

//        Disable Sidebar on create
        sidebar.setVisibility(View.GONE);

//        List Announcements from database
        final RelativeLayout announcementParentWrapper = binding.announcements;
        ArrayList<Announcement> announcementsSource = PlaceholderValues.generateTestAnnouncementList();
        int index = 0;
        for(Announcement announcement: announcementsSource) {
            View childView = getLayoutInflater().inflate(R.layout.announcement_item, null);
            String title = announcement.getTitle(), content = announcement.getDetails();
//            LinearLayout .LayoutParams layoutParams = (LinearLayout.LayoutParams) childView.getLayoutParams();

            TextView titleTextView = childView.findViewById(R.id.announcement_title_text);
            TextView contentTextView = childView.findViewById(R.id.announcement_content_text);

            titleTextView.setText(title);
            contentTextView.setText(content);

//            Log.i("test", layoutParams.toString());

            childView.findViewById(R.id.close_announcement).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    childView.setVisibility(View.GONE);
                }
            });

            announcementParentWrapper.addView(childView);
            index ++;
        }

//        Event from db
        //        List Events from database
//        final RelativeLayout eventParentWrapper = binding.events;
//        ArrayList<EventInfo> eventsSource = PlaceholderValues.generateTestEventInfoList();
//        int index2 = 0;
//        for(EventInfo event: eventsSource) {
//            View childView = getLayoutInflater().inflate(R.layout.event_item, null);
//            String title = event.getTitle(), content = event.getDetails();
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) childView.getLayoutParams();
//
//            TextView titleTextView = childView.findViewById(R.id.event_title);
//            TextView contentTextView = childView.findViewById(R.id.event_content);
//
//            titleTextView.setText(title);
//            contentTextView.setText(content);
//
//            if(layoutParams != null) {
//                layoutParams.topMargin = 200*index2 + 20;
//            }else{
//                Log.i("test", "bruh");
//            }
//
//            eventParentWrapper.addView(childView);
//            index2 ++;
//        }
        RecyclerView recyclerView = binding.RecyclerView;
        adapter = new EventAdapter(eventList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



//        Open Sidebar
        binding.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.setVisibility(View.VISIBLE);
            }
        });

//        Close Sidebar
        binding.menuIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.setVisibility(View.GONE);
            }
        });
        binding.sidebarWrapperBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sidebar.setVisibility(View.GONE);
            }
        });

//        Close Announcement
        binding.closeAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.announcement.setVisibility(View.GONE);
            }
        });
    }
}