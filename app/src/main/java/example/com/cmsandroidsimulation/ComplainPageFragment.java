package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import example.com.cmsandroidsimulation.databinding.ComplaintPageBinding;
import example.com.cmsandroidsimulation.presenters.Student;

public class ComplainPageFragment extends Fragment {

    private ComplaintPageBinding binding;
    private Student student;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ComplaintPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.complaintSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {

                    if (binding.complaintEditText.getText().toString().length() > 0
                            || binding.complaintEditText.getText().toString().length() < 640) {
                        Toast myToast = Toast.makeText(getActivity(),
                                "Please input complaint with maximum of 640 character.",
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }

                    if (!binding.complaintCheckBox.isChecked()) {
                        Toast myToast = Toast.makeText(getActivity(),
                                "Please check the box",
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }
//                    student.getInstance().getEmail();


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}