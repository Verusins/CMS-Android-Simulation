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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import example.com.cmsandroidsimulation.databinding.FragmentStudentBinding;
import example.com.cmsandroidsimulation.databinding.NavbarStudentBinding;

public final class StudentFragment extends Fragment {
    FragmentStudentBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavbarStudentBinding studentNavbar = binding.studentNavbar;

        studentNavbar.sidebarWrapperBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });

        studentNavbar.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentNavbar.sidebarWrapper.setVisibility(View.VISIBLE);
            }
        });
        studentNavbar.menuIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });
        studentNavbar.navigationHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.navhost_fragment_student);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.dashboardStudentFragment);

                studentNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });
        studentNavbar.navigationPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.navhost_fragment_student);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.postReqFragment);

                studentNavbar.sidebarWrapper.setVisibility(View.GONE);
            }
        });

//        ImageView navbarIcon = binding.dashboardStudent.menuIcon;
//
//        navbarIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sidebar.setEnabled(true);
//            }
//        });
    }
}
