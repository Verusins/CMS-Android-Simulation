package example.com.cmsandroidsimulation;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.time.LocalTime;
import java.util.Calendar;

import example.com.cmsandroidsimulation.databinding.FragmentAdminNewEventBinding;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public TextView fragment;
    private FragmentAdminNewEventBinding binding;
    int hour;
    int minute;

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    private void setHour(int hour) {
        this.hour = hour;
    }

    private void setMinute(int minute) {
        this.minute = minute;
    }

    public void setFragment(TextView fragment) { this.fragment = fragment; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(getActivity(), R.style.DialogTheme,this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time the user picks.
        LocalTime time = LocalTime.of(hourOfDay, minute);
        this.fragment.setText(time.toString());


    }
}
