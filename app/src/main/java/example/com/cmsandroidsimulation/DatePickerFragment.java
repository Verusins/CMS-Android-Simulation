package example.com.cmsandroidsimulation;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static int year;
    public static int month;
    public static int day;

    public TextView fragment;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public static void setYear(int year) {
        year = year;
    }

    public static void setMonth(int month) {
        month = month;
    }

    public static void setDay(int day) {
        day = day;
    }

    public void setFragment(TextView fragment) { this.fragment = fragment; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH);
        this.day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it.
        return new DatePickerDialog(requireContext(), R.style.DialogTheme, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date the user picks.
        LocalDate date = LocalDate.of(year, month + 1, day);
        this.fragment.setText(date.toString());
        Log.i("Date", "Year: " + year + "Month: " + month + "Day" + day);
    }
}
