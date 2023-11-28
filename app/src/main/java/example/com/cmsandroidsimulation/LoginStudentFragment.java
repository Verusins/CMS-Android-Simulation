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

import example.com.cmsandroidsimulation.databinding.FragmentLoginStudentBinding;
import example.com.cmsandroidsimulation.presenters.Student;

public final class LoginStudentFragment extends Fragment{
    FragmentLoginStudentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentLoginStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().runOnUiThread(() -> {
                    NavHostFragment.findNavController(LoginStudentFragment.this).
                            navigate(R.id.action_loginStudentFragment_to_studentRegisterFragment);
                });
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.emailEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();


                Student.Login(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            requireActivity().runOnUiThread(() -> {
                                NavHostFragment.findNavController(LoginStudentFragment.this).
                                        navigate(R.id.action_loginStudentFragment_to_studentFragment);
                            });
                        } else {
                            // show error message somewhere on the screen
                            throw new FailedLoginException();
                        }
                    }
                });
            }
        });
        binding.ToAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().runOnUiThread(() -> {
                NavHostFragment.findNavController(LoginStudentFragment.this).
                        navigate(R.id.action_loginStudentFragment_to_loginAdminFragment);
                });
            }
        });
    }
}
