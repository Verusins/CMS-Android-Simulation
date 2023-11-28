package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import example.com.cmsandroidsimulation.databinding.FragmentAdminBinding;
import example.com.cmsandroidsimulation.databinding.NavbarAdminBinding;

public final class AdminFragment extends Fragment {
    private FragmentAdminBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavbarAdminBinding adminNavbar = binding.adminNavbar;

        adminNavbar.sidebarWrapperBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });

        adminNavbar.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminNavbar.sidebarWrapper.setVisibility(View.VISIBLE);
            }
        });
        adminNavbar.menuIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });
        adminNavbar.navigationHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.navhost_fragment_student);
//                NavController navController = navHostFragment.getNavController();
//                navController.navigate(R.id.dashboardStudentFragment);
//
//                adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });
        adminNavbar.navigationCreateAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.navhost_fragment_student);
//                NavController navController = navHostFragment.getNavController();
//                navController.navigate(R.id.postReqFragment);
//
//                adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });
    }
}
