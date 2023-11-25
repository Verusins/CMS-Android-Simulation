package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class StudentEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Test","The last Log");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_student);

        // Add the fragment to the activity
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, new YourFragment())
//                .commit();
    }
}
