package example.com.cmsandroidsimulation;

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

import java.util.ArrayList;

import example.com.cmsandroidsimulation.databinding.FragmentEventStudentBinding;
import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

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

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load Content from db
        int eventIndex = getArguments().getInt("selectedEventIndex");
        // TODO: replace with fetch from backend/stashed event
        EventInfo eventInfo = PlaceholderValues.generateTestEventInfoList().get(eventIndex);
        binding.eventTitle.setText(eventInfo.getTitle());
        binding.eventContent.setText(eventInfo.getDetails());
        binding.eventAuthor.setText(eventInfo.getAuthor());

        // Disable the comment section / RSVP clicking
        binding.eventWriteCommentWrapper.setVisibility(View.GONE);
        binding.eventRSVPed.setVisibility(View.GONE);
        binding.eventRSVP.setVisibility(View.VISIBLE);
        // RSVP
        binding.eventRSVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.eventWriteCommentWrapper.setVisibility(View.VISIBLE);
                binding.eventRSVPed.setVisibility(View.VISIBLE);
                binding.eventRSVP.setVisibility(View.GONE);
            }
        });
        // Un-RSVP
        binding.eventRSVPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.eventWriteCommentWrapper.setVisibility(View.GONE);
                binding.eventRSVPed.setVisibility(View.GONE);
                binding.eventRSVP.setVisibility(View.VISIBLE);
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
                Log.i("Send", commentContent);
                // TODO: send commentContent (content) and rating[0] (rating(int)) to database
            }
        });



        // Comments loading
        LinearLayout commentsLayout = binding.comments;
        ArrayList<EventComment> eventComments = eventInfo.getComments();
        int index = 0;
        // TODO: Implement other event details.
        for(EventComment eventComment: eventComments) {
            View childView = getLayoutInflater().inflate(R.layout.event_comment_item, commentsLayout);
            ((TextView)childView.findViewById(R.id.comment_content)).setText(eventComment.getDetails());
        }


    }
}
