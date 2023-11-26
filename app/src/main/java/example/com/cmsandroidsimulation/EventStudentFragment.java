package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import example.com.cmsandroidsimulation.databinding.FragmentEventStudentBinding;
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

        binding.eventTitle.setText(getArguments().getString("eventTitle"));
        binding.eventContent.setText(getArguments().getString("eventContent"));
        binding.eventAuthor.setText(getArguments().getString("eventAuthor"));

//        final RelativeLayout eventParentWrapper = binding.eventMain;
//        final EventInfo eventSource = PlaceholderValues.generateTestEventInfoSingle();
//        View childView = getLayoutInflater().inflate(R.layout.fragment_event_student, null);
//        TextView titleTextView = childView.findViewById(R.id.event_title);
//        TextView authorTextView = childView.findViewById(R.id.event_author);
//        TextView contentTextView = childView.findViewById(R.id.event_content);
//        titleTextView.setText(eventSource.getTitle());
//        authorTextView.setText(eventSource.getAuthor());
//        contentTextView.setText(eventSource.getDetails());
//
//        eventParentWrapper.addView(childView);

//        Display Comments
    }
}
