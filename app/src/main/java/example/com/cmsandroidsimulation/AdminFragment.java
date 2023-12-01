package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;

import example.com.cmsandroidsimulation.databinding.FragmentAdminBinding;
import example.com.cmsandroidsimulation.presenters.Admin;

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
        binding.adminNavbar.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.adminNavbar.sidebarWrapper.setVisibility(View.VISIBLE);
            }
        });
        binding.adminNavbar.menuIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });
        binding.adminNavbar.navigationHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.navhost_fragment_admin);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.dashboardAdminFragment);

                binding.adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });
        binding.adminNavbar.navigationCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.navhost_fragment_admin);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.adminNewEventFragment);

                binding.adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });

        // Logout
        binding.adminNavbar.sidebarLogout.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {

                 Log.i("MASTER APP", "Logging out");
                 Log.i("MASTER APP", "logged out");
                 Admin.getInstance().Logout();
                 Log.i("MASTER APP", "navigating back to login screen");
                 NavController navController = NavHostFragment.findNavController(AdminFragment.this);
                 navController.navigate(R.id.loginAdminFragment);


                 // TODO: fix displaying navbar and logout student from the backend.
             }
         });
        binding.adminNavbar.navigationStudentComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.navhost_fragment_admin);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.complaintAdminFragment);

                binding.adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });

        binding.adminNavbar.navigationCreateAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.navhost_fragment_admin);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.adminCreateAnnouncementFragment);

                binding.adminNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });


//        Admin.getInstance().postEvent("Author 1", "Event 1", "Details 1",
//                new Date(123,1,2,3,4,5),
//                new Date(123,2,3,4,5,6), 2, );
//        Admin.getInstance().postEvent("Author 2", "Event 2", "Details 1",
//                new Date(124,2,2,3,4,5),
//                new Date(124,3,4,4,5,6), 4);
//        Admin.getInstance().postEvent("Author 3", "Event 3", "Details 1",
//                new Date(125,3,2,3,4,5),
//                new Date(125,4,5,4,5,6), 8);
//        Admin.getInstance().postEvent("Author 4", "Event 4", "Details 1",
//                new Date(126,4,2,3,4,5),
//                new Date(126,5,6,4,5,6), 16);
    }
}
