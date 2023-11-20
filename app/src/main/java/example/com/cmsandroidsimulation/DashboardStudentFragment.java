package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import example.com.cmsandroidsimulation.databinding.FragmentDashboardStudentBinding;

public class DashboardStudentFragment extends Fragment {
    FragmentDashboardStudentBinding binding;
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
