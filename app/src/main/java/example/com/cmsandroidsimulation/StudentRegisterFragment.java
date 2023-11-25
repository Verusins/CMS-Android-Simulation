package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import example.com.cmsandroidsimulation.databinding.FragmentRegisterStudentBinding;

public class StudentRegisterFragment extends Fragment {
    FragmentRegisterStudentBinding binding;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentRegisterStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.AlreadyHaveAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(StudentRegisterFragment.this).
                        navigate(R.id.action_studentRegisterFragment_to_loginStudentFragment);
            }
        });
    }

}
