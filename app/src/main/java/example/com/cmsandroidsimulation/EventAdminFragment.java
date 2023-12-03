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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import example.com.cmsandroidsimulation.databinding.FragmentEventAdminBinding;
import example.com.cmsandroidsimulation.datastructures.EventComment;
import example.com.cmsandroidsimulation.datastructures.EventInfo;
import example.com.cmsandroidsimulation.apiwrapper.Admin;

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
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm:ss", Locale.CANADA);
        binding.eventLocationAndTime.setText(dateFormat.format(eventInfo.getEventStartDateTime()) +
                " to " +
                dateFormat.format(eventInfo.getEventEndDateTime()) +
                " at " + eventInfo.getLocation());


        // Comments loading
        LinearLayout commentsLayout = binding.comments;
        ArrayList<EventComment> eventComments = eventInfo.getComments();
        int count = 0;
        double rating = 0;
        for (EventComment eventComment : eventComments) {
            View childView = getLayoutInflater().inflate(R.layout.event_comment_item, commentsLayout, false);
            commentsLayout.addView(childView);
            int commentRating = eventComment.getRating();

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
            DateFormat dateFormatComment = new SimpleDateFormat("dd MMM yyyy", Locale.CANADA);
            ((TextView)childView.findViewById(R.id.comment_date_and_time)).setText(dateFormatComment.format(eventComment.getDate()) + " by " + eventComment.getAuthor().charAt(0));

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