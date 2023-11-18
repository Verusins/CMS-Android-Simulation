package example.com.cmsandroidsimulation;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.emailEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();


                Student.Login(username, password).thenAccept(
                        student -> {
                            requireActivity().runOnUiThread(() -> {
                                NavHostFragment.findNavController(LoginStudentFragment.this).
                                        navigate(R.id.action_loginStudentFragment_to_studentFragment);
                            });
                        }
                ).exceptionally(throwable -> {
                    if(throwable instanceof FailedLoginException)
                    {
                        // show error message somewhere on the screen
                        return null;
                    }

                    Log.e("Login Error", throwable.toString());

                    return null;
                });
            }
        });
    }
}