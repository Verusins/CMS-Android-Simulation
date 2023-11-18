package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnnouncementFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard_student);

        // Assuming you have an Announcement object
        Announcement announcement = new Announcement();
        announcement.setTitle("Your Announcement Title");
        announcement.setDescription("This is the content of your announcement.");

        // Find the TextView in your layout by its ID
        TextView announcementTitleTextView = findViewById(R.id.announcementTitle);
        TextView announcementContentTextView = findViewById(R.id.announcementContent);

        // Set the text of the TextViews based on the Announcement object
        announcementTitleTextView.setText(announcement.getTitle());
        announcementContentTextView.setText(announcement.getDescription());
    }
}
