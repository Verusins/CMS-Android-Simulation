package example.com.cmsandroidsimulation;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.concurrent.CompletableFuture;

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
                            requireActivity().runOnUiThread(() -> {
                                NavHostFragment.findNavController(LoginFragment.this).
                                        navigate(R.id.action_loginFragment_to_studentFragment);
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

        binding.adminLoginConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.adminLoginUsernameEdit.getText().toString();
                String password = binding.adminLoginPasswordEdit.getText().toString();

                Admin.Login(username, password).thenAccept(
                        admin -> {
                            requireActivity().runOnUiThread(() -> {
                                NavHostFragment.findNavController(LoginFragment.this).
                                        navigate(R.id.action_loginFragment_to_adminFragment);
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
