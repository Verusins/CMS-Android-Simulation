package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import example.com.cmsandroidsimulation.databinding.CreateAnnouncementBinding;
import example.com.cmsandroidsimulation.apiwrapper.Admin;
import example.com.cmsandroidsimulation.apiwrapper.Student;

public class CreateAnnouncementFragment extends Fragment {

    private CreateAnnouncementBinding binding;
    private Student student;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = CreateAnnouncementBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.submitAnnouncementButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {

                    if (binding.announcementTitleInput.getText().toString().length() == 0
                            || binding.announcementTitleInput.getText().toString().length() > 40) {
                        Toast myToast = Toast.makeText(getActivity(),
                                "Please input announcement title with maximum of 40 character.",
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }

                    if (binding.AnnouncementDescription.getText().toString().length() == 0
                            || binding.AnnouncementDescription.getText().toString().length() > 200) {
                        Toast myToast = Toast.makeText(getActivity(),
                                "Please input announcement description with maximum of 200 character.",
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }

//                    Admin.getInstance().getName(Admin.getInstance().getEmail()).thenAccept((username) -> {
                        Log.i("MASTER APP", binding.announcementTitleInput.getText().toString());
                        Log.i("MASTER APP", binding.AnnouncementDescription.getText().toString());

                        Admin.getInstance().postAnnouncement(binding.announcementTitleInput.getText().toString(),
                                binding.AnnouncementDescription.getText().toString()
                        ).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

//                                requireActivity().runOnUiThread(() -> {
//                                    NavHostFragment.findNavController(CreateAnnouncementFragment.this).
//                                            navigate(R.id.dashboardAdminFragment);
//                                });
                                // TODO: link it to the backend
                                Toast myToast = Toast.makeText(getActivity(),
                                        "Announcement Submitted",
                                        Toast.LENGTH_SHORT);
                                myToast.show();

                                binding.announcementTitleInput.setText("");
                                binding.AnnouncementDescription.setText("");
                            }
                        });
//                    });

                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}