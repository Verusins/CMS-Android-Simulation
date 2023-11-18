package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import example.com.cmsandroidsimulation.databinding.FragmentPostreqBinding;

public class PostReqFragment extends Fragment {
    private FragmentPostreqBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPostreqBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.postQuizQ2.setVisibility(View.GONE);
        binding.postQuizQ3.setVisibility(View.GONE);

        Requirements req = new Requirements();
        view.findViewById(R.id.post_quiz_q1_cs_specialist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setTargetPost(getString(R.string.post_quiz_q1_cs_specialist));
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q1_cs_major).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setTargetPost(getString(R.string.post_quiz_q1_cs_major));
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });

    }
}
