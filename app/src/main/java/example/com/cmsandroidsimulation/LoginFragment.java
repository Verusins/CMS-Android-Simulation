package example.com.cmsandroidsimulation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import example.com.cmsandroidsimulation.databinding.FragmentLoginBinding;

public final class LoginFragment extends Fragment{

    FragmentLoginBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.studentLoginConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.studentLoginUsernameEdit.getText().toString();
                String password = binding.studentLoginPasswordEdit.getText().toString();

                Student.Login(username, password).thenAccept(
                        student -> {
                            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_studentFragment);
                        }
                );
            }
        });

        binding.adminLoginConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.adminLoginUsernameEdit.getText().toString();
                String password = binding.adminLoginPasswordEdit.getText().toString();

                Admin.Login(username, password).thenAccept(
                        admin -> {
                            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_adminFragment);
                        }
                );
            }
        });
    }
}
