package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import example.com.cmsandroidsimulation.databinding.FragmentStudentBinding;

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

//        binding.dashboardStudent.menuIcon.setOnClickListener(
//
//        );
    }
}
