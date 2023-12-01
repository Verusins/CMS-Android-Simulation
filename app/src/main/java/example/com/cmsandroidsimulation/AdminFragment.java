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
        binding.adminNavbar.sidebarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });
    }
}
