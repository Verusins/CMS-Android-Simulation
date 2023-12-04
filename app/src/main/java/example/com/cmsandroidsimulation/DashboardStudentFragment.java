package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import example.com.cmsandroidsimulation.apiwrapper.Admin;
import example.com.cmsandroidsimulation.databinding.FragmentDashboardStudentBinding;
import example.com.cmsandroidsimulation.datastructures.Announcement;
import example.com.cmsandroidsimulation.datastructures.EventInfo;
import example.com.cmsandroidsimulation.apiwrapper.Student;

public class DashboardStudentFragment extends Fragment {
    FragmentDashboardStudentBinding binding;
    EventAdapter adapter;
    AnnouncementAdapter announcementAdapter;

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

//        RelativeLayout sidebar = binding.sidebarWrapper;

//        Disable Sidebar on create
//        sidebar.setVisibility(View.GONE);

//        List Announcements from database

        Log.i("test0", "test0");
        Student.getInstance().getAnnouncements().thenAccept((ArrayList<Announcement> announcements) -> {
            Log.i("test1", "test1");
            RecyclerView recyclerViewAnnouncement = binding.RecyclerViewAnnouncement;
            Log.i("test2", "test2");
            recyclerViewAnnouncement.setNestedScrollingEnabled(false);
            announcementAdapter = new AnnouncementAdapter(announcements, getContext());

            Log.i("test3", "test3");
            recyclerViewAnnouncement.setAdapter(announcementAdapter);
            recyclerViewAnnouncement.setLayoutManager(new LinearLayoutManager(getContext()));
        });


//        Event from db
        Student.getInstance().getEvents().thenAccept((ArrayList<EventInfo> eventList) -> {
            Log.i("MASTER APP", "events: " + eventList);
            RecyclerView recyclerView = binding.RecyclerView;
            recyclerView.setNestedScrollingEnabled(false);
            adapter = new EventAdapter(eventList, getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });

    }
}