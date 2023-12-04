package example.com.cmsandroidsimulation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import example.com.cmsandroidsimulation.databinding.FragmentEventStudentBinding;
import example.com.cmsandroidsimulation.datastructures.EventComment;
import example.com.cmsandroidsimulation.datastructures.EventInfo;
import example.com.cmsandroidsimulation.apiwrapper.Student;

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
        final int[] registeredMembers = {eventInfo.getAttendees().size()};
        int maxMembers = eventInfo.getMaxppl();
        boolean full = registeredMembers[0] >= maxMembers;
        binding.eventMembers.setText("Registered: " + registeredMembers[0] + "/" + maxMembers);

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

        if(full)
            binding.eventRSVP.setVisibility(View.GONE);


        // RSVP
        binding.eventRSVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.eventWriteCommentWrapper.setVisibility(View.VISIBLE);
                binding.eventRSVPed.setVisibility(View.VISIBLE);
                binding.eventRSVP.setVisibility(View.GONE);
                registeredMembers[0]++;
                binding.eventMembers.setText("Registered: " + registeredMembers[0] + "/" + maxMembers);

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
                registeredMembers[0]--;
                binding.eventMembers.setText("Registered: " + registeredMembers[0] + "/" + maxMembers);

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
                if(rating[0] == -1) {
                    Toast myToast = Toast.makeText(getActivity(),
                            "You must click a rating!",
                            Toast.LENGTH_SHORT);
                    myToast.show();
                }else {
                    Task<DocumentSnapshot> task = Student.getInstance().postEventComment(eventInfo, binding.commentContentWrite.getText().toString(), rating[0]);
                    task.addOnSuccessListener(
                            new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            requireActivity().runOnUiThread(() -> {
                                                Bundle bundle = new Bundle();
                                                bundle.putInt("selectedEventIndex", getArguments().getInt("selectedEventIndex"));
                                                NavHostFragment.findNavController(EventStudentFragment.this).navigate(R.id.eventFragment, bundle);
                                                // TODO: add toast
                                            });
                                        }
                                    }, 1000);
                                }
                            }
                    );
                    task.addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // TODO: add toast
                                }
                            }
                    );
                }
            }
        });



        // Comments loading
        LinearLayout commentsLayout = binding.comments;
        // TODO: Implement other event details.
        for(EventComment eventComment: eventInfo.getComments()) {
            View childView = getLayoutInflater().inflate(R.layout.event_comment_item, commentsLayout, false);
            Log.i("MASTER APP", "RENDER EVENT COMMENT");
            int commentRating = eventComment.getRating();


            ((TextView)childView.findViewById(R.id.comment_content)).setText(eventComment.getDetails());

            if(commentRating == 1) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★☆☆☆☆");
            if(commentRating == 2) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★★☆☆☆");
            if(commentRating == 3) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★★★☆☆");
            if(commentRating == 4) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★★★★☆");
            if(commentRating == 5) ((TextView)childView.findViewById(R.id.comments_rating)).setText("★★★★★");

            DateFormat dateFormatComment = new SimpleDateFormat("dd MMM yyyy", Locale.CANADA);
            ((TextView)childView.findViewById(R.id.comment_date_and_time)).setText(dateFormatComment.format(eventComment.getDate()) + " by " + eventComment.getAuthor().charAt(0));

            commentsLayout.addView(childView);

        }
    }
}
