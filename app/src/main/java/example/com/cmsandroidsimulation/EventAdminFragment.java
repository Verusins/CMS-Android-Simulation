package example.com.cmsandroidsimulation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import example.com.cmsandroidsimulation.databinding.FragmentEventAdminBinding;
import example.com.cmsandroidsimulation.databinding.FragmentEventStudentBinding;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;
import example.com.cmsandroidsimulation.presenters.Admin;

public class EventAdminFragment extends Fragment {
    FragmentEventAdminBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentEventAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load Content from db
        int eventIndex = getArguments().getInt("selectedEventIndex");

        Admin.getInstance().getEvents().thenAccept((eventInfos) -> {
           afterEventFetch(eventInfos.get(eventIndex));
        });
    }
    private void afterEventFetch(EventInfo eventInfo)
    {
        binding.eventTitle.setText(eventInfo.getTitle());
        binding.eventContent.setText(eventInfo.getDetails());
        binding.eventAuthor.setText(eventInfo.getAuthor());


        // Comments loading
        LinearLayout commentsLayout = binding.comments;
        ArrayList<EventComment> eventComments = eventInfo.getComments();
        int count = 0;
        double rating = 0;
        for (EventComment eventComment : eventComments) {
            View childView = getLayoutInflater().inflate(R.layout.event_comment_item, commentsLayout, false);
            commentsLayout.addView(childView);
            int commentRating = eventComment.getRating();
            String[] month = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String day = String.valueOf(eventComment.getDate().getDay());
            String year = String.valueOf(eventComment.getDate().getYear());
            String commentAuthor = String.valueOf(eventComment.getAuthor());
            String commentInfo = month[eventComment.getDate().getMonth()] + " " + day + ", " + year + " by " + commentAuthor.charAt(0);


            ((TextView) childView.findViewById(R.id.comment_content)).setText(eventComment.getDetails());

            if (commentRating == 1)
                ((TextView) childView.findViewById(R.id.comments_rating)).setText("★☆☆☆☆");
            if (commentRating == 2)
                ((TextView) childView.findViewById(R.id.comments_rating)).setText("★★☆☆☆");
            if (commentRating == 3)
                ((TextView) childView.findViewById(R.id.comments_rating)).setText("★★★☆☆");
            if (commentRating == 4)
                ((TextView) childView.findViewById(R.id.comments_rating)).setText("★★★★☆");
            if (commentRating == 5)
                ((TextView) childView.findViewById(R.id.comments_rating)).setText("★★★★★");

            ((TextView) childView.findViewById(R.id.comment_date_and_time)).setText(commentInfo);
            count ++;
            rating += eventComment.getRating();
        }
        rating /= count;
        binding.eventRating.setText(String.format("%.1f", rating));
        binding.eventRatingCount.setText(String.valueOf(count));
        Log.i("MASTER APP", "event max: " + eventInfo.getMaxppl());
        Log.i("MASTER APP", "event registered: " + eventInfo.getAttendees().size());
        Log.i("MASTER APP", "event max binding: " +  binding.eventMaxCapacity);
        Log.i("MASTER APP", "event registered binding: " + binding.eventRegisteredNo);
        binding.eventMaxCapacity.setText(eventInfo.getMaxppl() + "");
        binding.eventRegisteredNo.setText(eventInfo.getAttendees().size() + "");
        Log.i("MASTER APP", "finished event render");
    }
}