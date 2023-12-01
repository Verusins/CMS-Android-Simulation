package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import example.com.cmsandroidsimulation.databinding.FragmentAdminNewEventBinding;
import example.com.cmsandroidsimulation.presenters.Admin;

public class AdminNewEventFragment extends Fragment {
    private FragmentAdminNewEventBinding binding;

    private LocalDateTime start;
    private LocalDateTime end;

    private TextView start_date_text;
    private TextView start_time_text;
    private TextView end_date_text;
    private TextView end_time_text;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAdminNewEventBinding.inflate(inflater, container, false);
        start_date_text = binding.startPickDate;
        start_time_text = binding.startPickTime;
        end_date_text = binding.endPickDate;
        end_time_text = binding.endPickTime;
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.startPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getParentFragmentManager(), "datePicker");
                dateFragment.setFragment(start_date_text);
            }
        });

        binding.startPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getParentFragmentManager(), "timePicker");
                timeFragment.setFragment(start_time_text);

            }
        });

        binding.endPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getParentFragmentManager(), "datePicker");
                dateFragment.setFragment(end_date_text);

            }
        });

        binding.endPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getParentFragmentManager(), "timePicker");
                timeFragment.setFragment(end_time_text);

            }
        });

        binding.adminEventsSubmit.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view){
                try {

                    if (binding.adminNewEventNameInput.getText().toString().equals("")
                            || binding.adminNewEventDescInput.getText().toString().equals("")
                            || binding.adminEventParticipantsInput.getText().toString().equals("")
                            || binding.adminEventLocationInput.getText().toString().equals("")
                            || binding.startPickDate.getText().toString().equals("")
                            || binding.startPickTime.getText().toString().equals("")
                            || binding.endPickDate.getText().toString().equals("")
                            || binding.endPickTime.getText().toString().equals("")){

                        Toast myToast = Toast.makeText(getActivity(),
                                getContext().getString(R.string.admin_event_input_error),
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }

                    if (binding.adminNewEventNameInput.getText().toString().length() > 200){
                        Toast myToast = Toast.makeText(getActivity(),
                                getContext().getString(R.string.admin_event_invalid_event_name),
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }

                    if (binding.adminNewEventDescInput.getText().toString().length() > 200){
                        Toast myToast = Toast.makeText(getActivity(),
                                getContext().getString(R.string.admin_event_invalid_event_desc),
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }
                    LocalDate start_date = LocalDate.parse(start_date_text.getText());
                    LocalTime start_time = LocalTime.parse(start_time_text.getText());
                    start = LocalDateTime.of(start_date, start_time);

                    LocalDate end_date = LocalDate.parse(end_date_text.getText());
                    LocalTime end_time = LocalTime.parse(end_time_text.getText());
                    end = LocalDateTime.of(end_date, end_time);

                    if (start.isBefore(LocalDateTime.now())){
                        Toast myToast = Toast.makeText(getActivity(),
                                getContext().getString(R.string.admin_event_invalid_start_time),
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }

                    if (end.isBefore((start))){
                        Toast myToast = Toast.makeText(getActivity(),
                                getContext().getString(R.string.admin_event_invalid_end_time),
                                Toast.LENGTH_SHORT);
                        myToast.show();
                        return;
                    }
                    Admin.getInstance().getName(Admin.getInstance().getEmail()).thenAccept((username) -> {
                        Log.i("MASTER APP", binding.adminNewEventNameInput.getText().toString());
                        Log.i("MASTER APP", binding.adminNewEventDescInput.getText().toString());
                        Log.i("MASTER APP", Date.from(start.atZone(ZoneId.systemDefault()).toInstant())+"");
                        Log.i("MASTER APP", Date.from(end.atZone(ZoneId.systemDefault()).toInstant()) +"");
                        Log.i("MASTER APP", Integer.parseInt(binding.adminEventParticipantsInput.getText().toString())+"");

                        Admin.getInstance().postEvent(username,
                                binding.adminNewEventNameInput.getText().toString(),
                                binding.adminNewEventDescInput.getText().toString(),
                                Date.from(start.atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(end.atZone(ZoneId.systemDefault()).toInstant()),
                                Integer.parseInt(binding.adminEventParticipantsInput.getText().toString()),
                                binding.adminEventLocationInput.getText().toString()
                                ).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                requireActivity().runOnUiThread(() -> {
                                    NavHostFragment.findNavController(AdminNewEventFragment.this).
                                            navigate(R.id.dashboardAdminFragment);
                                });
                            }
                        });
                    });
                }catch (Exception e){
                    Log.i("", "An error has occurred");
                }
            }
        });

    }
}
