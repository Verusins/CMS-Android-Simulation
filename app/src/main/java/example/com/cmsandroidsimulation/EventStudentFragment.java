package example.com.cmsandroidsimulation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import example.com.cmsandroidsimulation.databinding.FragmentEventStudentBinding;
import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;
import example.com.cmsandroidsimulation.presenters.Admin;
import example.com.cmsandroidsimulation.presenters.Student;

public class EventStudentFragment extends Fragment {
    FragmentEventStudentBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentEventStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load Content from db
        int eventIndex = getArguments().getInt("selectedEventIndex");
        // TODO: replace with fetch from backend/stashed event

        Student.getInstance().getEvents().thenAccept((ArrayList<EventInfo> events) -> {
            try {
                afterFetchEventInfo(events.get(eventIndex));
            }
            catch (Exception e)
            {
                Log.e("MASTER APP", e.toString());
            }
        });


    }
    private void afterFetchEventInfo(EventInfo eventInfo)
    {
        Log.i("MASTER APP", "RSVP INFO");
        Log.i("MASTER APP", eventInfo.getAttendees().toString());
        Log.i("MASTER APP", Student.getInstance().getEmail());
        Log.i("MASTER APP", "dateFormat.format(eventInfo.getEventStartDateTime())");
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm:ss", Locale.CANADA);
        Log.i("MASTER APP", "dateFormat.format(eventInfo.getEventStartDateTime())");
        Log.i("MASTER APP", eventInfo.getEventStartDateTime() +"");
        Log.i("MASTER APP", dateFormat.format(eventInfo.getEventStartDateTime()));

        binding.eventTitle.setText(eventInfo.getTitle());
        binding.eventContent.setText(eventInfo.getDetails());
        binding.eventAuthor.setText(eventInfo.getAuthor());
        Log.i("MASTER APP", dateFormat.format(eventInfo.getEventStartDateTime()));
        binding.eventLocationAndTime.setText(dateFormat.format(eventInfo.getEventStartDateTime()) +
                " to " +
                dateFormat.format(eventInfo.getEventEndDateTime()) +
                " at " + eventInfo.getLocation());

        // Disable the comment section / RSVP clicking
        binding.eventWriteCommentWrapper.setVisibility(View.GONE);
        binding.eventRSVPed.setVisibility(View.GONE);
        binding.eventRSVP.setVisibility(View.VISIBLE);

        if (eventInfo.getAttendees().contains(Student.getInstance().getEmail())){
            Log.i("MASTER APP", "RSVPED ALREADY");
            binding.eventWriteCommentWrapper.setVisibility(View.VISIBLE);
            binding.eventRSVPed.setVisibility(View.VISIBLE);
            binding.eventRSVP.setVisibility(View.GONE);
        }
        // RSVP
        binding.eventRSVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.eventWriteCommentWrapper.setVisibility(View.VISIBLE);
                binding.eventRSVPed.setVisibility(View.VISIBLE);
                binding.eventRSVP.setVisibility(View.GONE);

                Student.getInstance().setEventHasRSVPd(eventInfo, true);
            }
        });
        // Un-RSVP
        binding.eventRSVPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.eventWriteCommentWrapper.setVisibility(View.GONE);
                binding.eventRSVPed.setVisibility(View.GONE);
                binding.eventRSVP.setVisibility(View.VISIBLE);

                Student.getInstance().setEventHasRSVPd(eventInfo, false);
            }
        });

        // Click rating and change rating
        final int[] rating = {-1};
        binding.commentWriteRating1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating[0] = 1;
                binding.commentWriteRating1.setText("★");
                binding.commentWriteRating2.setText("☆");
                binding.commentWriteRating3.setText("☆");
                binding.commentWriteRating4.setText("☆");
                binding.commentWriteRating5.setText("☆");
            }
        });
        binding.commentWriteRating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating[0] = 2;
                binding.commentWriteRating1.setText("★");
                binding.commentWriteRating2.setText("★");
                binding.commentWriteRating3.setText("☆");
                binding.commentWriteRating4.setText("☆");
                binding.commentWriteRating5.setText("☆");
            }
        });
        binding.commentWriteRating3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating[0] = 3;
                binding.commentWriteRating1.setText("★");
                binding.commentWriteRating2.setText("★");
                binding.commentWriteRating3.setText("★");
                binding.commentWriteRating4.setText("☆");
                binding.commentWriteRating5.setText("☆");
            }
        });
        binding.commentWriteRating4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating[0] = 4;
                binding.commentWriteRating1.setText("★");
                binding.commentWriteRating2.setText("★");
                binding.commentWriteRating3.setText("★");
                binding.commentWriteRating4.setText("★");
                binding.commentWriteRating5.setText("☆");
            }
        });
        binding.commentWriteRating5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating[0] = 5;
                binding.commentWriteRating1.setText("★");
                binding.commentWriteRating2.setText("★");
                binding.commentWriteRating3.setText("★");
                binding.commentWriteRating4.setText("★");
                binding.commentWriteRating5.setText("★");
            }
        });


        // Submit Comment
        binding.commentWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentContent = String.valueOf(binding.commentContentWrite.getText());
                Student.getInstance().postEventComment(eventInfo, binding.commentContentWrite.getText().toString(), rating[0]);
                eventInfo.getComments().add(new EventComment("Me",  binding.
                        commentContentWrite.getText().toString(), rating[0], new Date()));
                // Empty input field
                binding.commentWriteRating1.setText("☆");
                binding.commentWriteRating2.setText("☆");
                binding.commentWriteRating3.setText("☆");
                binding.commentWriteRating4.setText("☆");
                binding.commentWriteRating5.setText("☆");
                binding.commentContentWrite.setText("");
                // Log.i("Send", commentContent);
                // TODO: send commentContent (content) and rating[0] (rating(int)) to database

                rating[0] = -1;
                binding.comments.removeAllViews();
                afterFetchEventInfo(eventInfo);
            }
        });



        // Comments loading
        LinearLayout commentsLayout = binding.comments;
        // TODO: Implement other event details.
        for(EventComment eventComment: eventInfo.getComments()) {
            View childView = getLayoutInflater().inflate(R.layout.event_comment_item, commentsLayout, false);
            Log.i("MASTER APP", "RENDER EVENT COMMENT");
            int commentRating = eventComment.getRating();
            String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String day = String.valueOf(eventComment.getDate().getDay());
            String year = String.valueOf(eventComment.getDate().getYear()+ 1900) ;
            String commentAuthor = String.valueOf(eventComment.getAuthor());
            String commentInfo = month[eventComment.getDate().getMonth()] + " " + day + ", " + year + " by " + commentAuthor.charAt(0);


            ((TextView)childView.findViewById(R.id.comment_content)).setText(eventComment.getDetails());

            if(commentRating == 1) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★☆☆☆☆");
            if(commentRating == 2) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★★☆☆☆");
            if(commentRating == 3) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★★★☆☆");
            if(commentRating == 4) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★★★★☆");
            if(commentRating == 5) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★★★★★");

            ((TextView)childView.findViewById(R.id.comment_date_and_time)).setText(commentInfo);

            commentsLayout.addView(childView);

        }
    }
}
