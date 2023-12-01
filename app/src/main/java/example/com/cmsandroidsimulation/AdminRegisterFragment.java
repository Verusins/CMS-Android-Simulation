package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

import java.util.regex.Pattern;

import example.com.cmsandroidsimulation.databinding.FragmentRegisterAdminBinding;
import example.com.cmsandroidsimulation.presenters.Admin;

public class AdminRegisterFragment extends Fragment {
    FragmentRegisterAdminBinding binding;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentRegisterAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.AlreadyHaveAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().runOnUiThread(() -> {
                    NavHostFragment.findNavController(AdminRegisterFragment.this).
                            navigate(R.id.action_adminRegisterFragment_to_loginStudentFragment);
                });
            }
        });
        binding.StudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().runOnUiThread(() -> {
                    NavHostFragment.findNavController(AdminRegisterFragment.this).
                            navigate(R.id.action_adminRegisterFragment_to_studentRegisterFragment);
                });
            }
        });
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.usernameEditText.getText().toString();
                String email = binding.emailEditText.getText().toString();
                String password = binding.signupPasswordEditText.getText().toString();
                CheckBox checkbox = binding.termsCheckBox;

                String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
                Pattern pattern = Pattern.compile(emailRegex);
                if (!pattern.matcher(email).matches()){
                    Toast myToast = Toast.makeText(getActivity(),
                            "Please input valid Email",
                            Toast.LENGTH_SHORT);
                    myToast.show();
                    return;
                }
                if (username.equals("") || !checkbox.isChecked()
                        || email.equals("") || password.equals("")){
                    Toast myToast = Toast.makeText(getActivity(),
                            "Please fill all the box",
                            Toast.LENGTH_SHORT);
                    myToast.show();
                    return;
                }


                Admin.Register(username, email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        requireActivity().runOnUiThread(() -> {
                            if (task.isSuccessful()) {
                                NavHostFragment.findNavController(AdminRegisterFragment.this).navigate(R.id.adminFragment);
                            }else {
                                Toast myToast = Toast.makeText(getActivity(),
                                        task.getException().getMessage(),
                                        Toast.LENGTH_SHORT);
                                myToast.show();
                            }
                        });
                    }});

            }
        });
    }

}
