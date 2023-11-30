package example.com.cmsandroidsimulation;

import android.os.Bundle;
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

import example.com.cmsandroidsimulation.databinding.FragmentDashboardAdminBinding;
import example.com.cmsandroidsimulation.databinding.FragmentDashboardStudentBinding;
import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;
import example.com.cmsandroidsimulation.presenters.Admin;

public class DashboardAdminFragment extends Fragment {

    FragmentDashboardAdminBinding binding;
    EventAdapter eventAdapter;
    AnnouncementAdapter announcementAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDashboardAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        RelativeLayout sidebar = binding.sidebarWrapper;

//        Disable Sidebar on create
//        sidebar.setVisibility(View.GONE);

//        Announcement from db
        ArrayList<Announcement> announcementList = PlaceholderValues.generateTestAnnouncementList();

        Admin.getInstance().getEvents().thenAccept((ArrayList<EventInfo> eventList) -> {

            RecyclerView recyclerViewAnnouncement = binding.RecyclerViewAnnouncement;
            recyclerViewAnnouncement.setNestedScrollingEnabled(false);
            announcementAdapter = new AnnouncementAdapter(announcementList, getContext());
            recyclerViewAnnouncement.setAdapter(announcementAdapter);
            recyclerViewAnnouncement.setLayoutManager(new LinearLayoutManager(getContext()));
            RecyclerView recyclerViewEvent = binding.RecyclerViewEvent;
            recyclerViewEvent.setNestedScrollingEnabled(false);
            eventAdapter = new EventAdapter(eventList, getContext());
            recyclerViewEvent.setAdapter(eventAdapter);
            recyclerViewEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        });



    }
}
