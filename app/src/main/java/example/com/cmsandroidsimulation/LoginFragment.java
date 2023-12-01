package example.com.cmsandroidsimulation;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import example.com.cmsandroidsimulation.databinding.FragmentLoginBinding;
import example.com.cmsandroidsimulation.presenters.Admin;
import example.com.cmsandroidsimulation.presenters.Student;
import example.com.cmsandroidsimulation.presenters.User;

public final class LoginFragment extends Fragment {
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
        binding.CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().runOnUiThread(() -> {
                    NavHostFragment.findNavController(LoginFragment.this).
                            navigate(R.id.action_loginStudentFragment_to_studentRegisterFragment);
                });
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();

                if (email.equals("") || password.equals("")) {
                    Toast myToast = Toast.makeText(getActivity(),
                            "Please fill all the box",
                            Toast.LENGTH_SHORT);
                    myToast.show();
                    return;
                }
                CompletableFuture<User> cf = User.Login(email, password);
                cf.thenAccept(user -> {
                    if(user instanceof Admin)
                    {
                        requireActivity().runOnUiThread(() -> {
                            NavHostFragment.findNavController(LoginFragment.this).
                                    navigate(R.id.adminFragment);
                        });
                    }
                    else
                    {
                        requireActivity().runOnUiThread(() -> {
                            NavHostFragment.findNavController(LoginFragment.this).
                                    navigate(R.id.studentFragment);
                        });
                    }
                });
                cf.exceptionally((e)-> {
                    requireActivity().runOnUiThread(() -> {
                        Toast myToast = Toast.makeText(getActivity(),
                                e.getMessage(),
                                Toast.LENGTH_SHORT);
                        myToast.show();
                    });
                    return null;
                });
            }
        });
    }
}
