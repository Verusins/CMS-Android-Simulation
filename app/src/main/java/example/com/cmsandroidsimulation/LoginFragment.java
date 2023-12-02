package example.com.cmsandroidsimulation;


import android.app.ProgressDialog;
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
import example.com.cmsandroidsimulation.models.LoginModel;
import example.com.cmsandroidsimulation.presenters.Admin;
import example.com.cmsandroidsimulation.presenters.LoginPresenter;
import example.com.cmsandroidsimulation.presenters.Student;
import example.com.cmsandroidsimulation.presenters.User;
import example.com.cmsandroidsimulation.views.LoginView;

public class LoginFragment extends Fragment implements LoginView {
    FragmentLoginBinding binding;
    private ProgressDialog progressDialog;
    private LoginPresenter loginPresenter;

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
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Logging in...");

        loginPresenter = new LoginPresenter(this, new LoginModel());

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
                loginPresenter.validateCredentials(email, password);
            }
        });
    }

    @Override
    public void showUsernameError() {
        Toast myToast = Toast.makeText(getActivity(), "Wrong Username or Password!", Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    public void showPasswordError() {
        Toast myToast = Toast.makeText(getActivity(), "Wrong Username or Password!", Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    public void showLoginSuccess(User user) {
        progressDialog.dismiss();
        if (user instanceof Admin) {
            requireActivity().runOnUiThread(() -> {
                NavHostFragment.findNavController(LoginFragment.this).
                        navigate(R.id.adminFragment);
            });
        } else {
            requireActivity().runOnUiThread(() -> {
                NavHostFragment.findNavController(LoginFragment.this).
                        navigate(R.id.studentFragment);
            });
        }
    }

    @Override
    public void showLoginFailed(String errorMessage) {
        progressDialog.dismiss();
        requireActivity().runOnUiThread(() -> {
            Log.i("error login message", errorMessage);
            Toast myToast = Toast.makeText(getActivity(), "Wrong Username or Password!", Toast.LENGTH_SHORT);
            myToast.show();
        });
    }
}