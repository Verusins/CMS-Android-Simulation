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

import java.util.concurrent.atomic.AtomicBoolean;

import example.com.cmsandroidsimulation.databinding.FragmentLoginBinding;
import example.com.cmsandroidsimulation.presenters.Admin;
import example.com.cmsandroidsimulation.presenters.Student;

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

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference dataCollection = db.collection("users");
                Query query = dataCollection.whereEqualTo("email", email); // if this is not empty i want to make toast
                AtomicBoolean checkemail = new AtomicBoolean(false);
                AtomicBoolean userisadmin = new AtomicBoolean(false);
                query.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Email exists, get other field (e.g., "isAdmin")
                                Log.i("login check", "ok");
                                checkemail.set(true);
                                userisadmin.set(document.getBoolean("isAdmin"));
                            }
                            if (!checkemail.get()) {
                                Toast myToast = Toast.makeText(getActivity(),
                                        "Please Register your Email",
                                        Toast.LENGTH_SHORT);
                                myToast.show();
                            } else if (userisadmin.get()) {
                                Admin.Login(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            requireActivity().runOnUiThread(() -> {
                                                NavHostFragment.findNavController(LoginFragment.this).
                                                        navigate(R.id.action_loginStudentFragment_to_AdminFragment);
                                            });
                                        } else {
                                            // show error message somewhere on the screen
                                            throw new FailedLoginException();
                                        }
                                    }
                                });
                            } else {
                                Student.Login(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            requireActivity().runOnUiThread(() -> {
                                                NavHostFragment.findNavController(LoginFragment.this).
                                                        navigate(R.id.action_loginStudentFragment_to_studentFragment);
                                            });
                                        } else {
                                            // show error message somewhere on the screen
                                            throw new FailedLoginException();
                                        }
                                    }
                                });
                            }
                        } else {
                            Toast myToast = Toast.makeText(getActivity(),
                                    "Please Register your Email",
                                    Toast.LENGTH_SHORT);
                            myToast.show();
                            // show error message somewhere on the screen
                            Log.w("MASTER APP", "Student login failed", task.getException());
                        }
                    } else {
                        // Handle errors
                        Log.e("MASTER APP", "Error getting documents: ", task.getException());
                    }
                });
            }
        });
    }
}
