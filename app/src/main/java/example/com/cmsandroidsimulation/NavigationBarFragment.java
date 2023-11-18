package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NavigationBarFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard_student, container, false);

        // Access the ImageView inside the inflated layout
        ImageView menuIcon = rootView.findViewById(R.id.menu);

        // Set up click listener for the menu icon
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle menu icon click
                // For example, open the drawer or perform any desired action
            }
        });

        return rootView;
    }
}

