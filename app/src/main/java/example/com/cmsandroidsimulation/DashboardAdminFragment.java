package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import example.com.cmsandroidsimulation.databinding.FragmentDashboardAdminBinding;

public class DashboardAdminFragment extends Fragment {

    FragmentDashboardAdminBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDashboardAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
