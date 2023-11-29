package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import example.com.cmsandroidsimulation.databinding.FragmentAdminNewEventBinding;

public class AdminEventFragment extends Fragment {
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
                    }

                    if (binding.adminNewEventNameInput.getText().toString().length() > 200){
                        Toast myToast = Toast.makeText(getActivity(),
                                getContext().getString(R.string.admin_event_invalid_event_name),
                                Toast.LENGTH_SHORT);
                        myToast.show();
                    }

                    if (binding.adminNewEventDescInput.getText().toString().length() > 200){
                        Toast myToast = Toast.makeText(getActivity(),
                                getContext().getString(R.string.admin_event_invalid_event_desc),
                                Toast.LENGTH_SHORT);
                        myToast.show();
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
                    }

                    if (end.isBefore((start))){
                        Toast myToast = Toast.makeText(getActivity(),
                                getContext().getString(R.string.admin_event_invalid_end_time),
                                Toast.LENGTH_SHORT);
                        myToast.show();
                    }

                    Log.i("", start.toString() + end.toString());
                }catch (Exception e){
                    Log.i("", "An error has occurred");
                }
            }
        });

    }
}
