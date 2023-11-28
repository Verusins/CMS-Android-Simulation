package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.time.LocalDateTime;

import example.com.cmsandroidsimulation.databinding.FragmentAdminNewEventBinding;

public class AdminEventFragment extends Fragment {
    private FragmentAdminNewEventBinding binding;

    public int start_year;
    public int start_month;
    public int start_day;
    int start_hour;
    int start_minute;

    public int end_year;
    public int end_month;
    public int end_day;
    int end_hour;
    int end_minute;

    LocalDateTime start;
    LocalDateTime end;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAdminNewEventBinding.inflate(inflater, container, false);
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

                start_year = dateFragment.getYear();
                start_month = dateFragment.getMonth();
                start_day = dateFragment.getDay();
            }
        });

        binding.startPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getParentFragmentManager(), "timePicker");

                start_hour = timeFragment.getHour();
                start_minute = timeFragment.getMinute();

            }
        });

        binding.endPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getParentFragmentManager(), "datePicker");

                end_year = dateFragment.getYear();
                end_month = dateFragment.getMonth();
                end_day = dateFragment.getDay();

            }
        });

        binding.endPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getParentFragmentManager(), "timePicker");

                end_hour = timeFragment.getHour();
                end_minute = timeFragment.getMinute();

            }
        });

        binding.adminEventsSubmit.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view){
                try {
                    start = LocalDateTime.of(start_year, start_month, start_day, start_hour, start_minute);
                }catch (Exception e){
                    Log.i("", "An error has occurred");
                }
            }
        });

    }
}
