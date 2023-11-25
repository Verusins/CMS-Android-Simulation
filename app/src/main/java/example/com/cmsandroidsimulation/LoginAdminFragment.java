package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import example.com.cmsandroidsimulation.databinding.FragmentLoginAdminBinding;
import example.com.cmsandroidsimulation.presenters.Admin;

public class LoginAdminFragment  extends Fragment {
    FragmentLoginAdminBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentLoginAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginAdminFragment.this).
                        navigate(R.id.action_loginAdminFragment_to_studentRegisterFragment);
            }
        });
        binding.ToStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginAdminFragment.this).
                        navigate(R.id.action_loginAdminFragment_to_loginStudentFragment);
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.emailEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();
                Admin.Login(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            requireActivity().runOnUiThread(() -> {
                                NavHostFragment.findNavController(LoginAdminFragment.this).
                                        navigate(R.id.action_loginAdminFragment_to_adminFragment);
                            });
                        } else {
                            // show error message somewhere on the screen
                        }

                    }
                });
            }
        });
    }
}
