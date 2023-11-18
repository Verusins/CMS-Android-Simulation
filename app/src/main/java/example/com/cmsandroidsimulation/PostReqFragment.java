package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import example.com.cmsandroidsimulation.databinding.FragmentPostreqBinding;

public class PostReqFragment extends Fragment {
    private FragmentPostreqBinding binding;
    private TextView post_results;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPostreqBinding.inflate(inflater, container, false);
        post_results = binding.postQuizResultsDesc;
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.postQuizQ2.setVisibility(View.GONE);
        binding.postQuizQ3.setVisibility(View.GONE);
        binding.postQuizSubmit.setVisibility(View.GONE);
        binding.postQuizResults.setVisibility(View.GONE);

        Requirements req = new Requirements();

        // Question 1
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

        view.findViewById(R.id.post_quiz_q1_cs_minor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setTargetPost(getString(R.string.post_quiz_q1_cs_minor));
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q1_stats_specialist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setTargetPost(getString(R.string.post_quiz_q1_stats_specialist));
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q1_stats_major).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setTargetPost(getString(R.string.post_quiz_q1_stats_major));
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q1_stats_minor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setTargetPost(getString(R.string.post_quiz_q1_stats_minor));
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q1_math_specialist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setTargetPost(getString(R.string.post_quiz_q1_math_specialist));
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q1_math_major).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setTargetPost(getString(R.string.post_quiz_q1_math_major));
                binding.postQuizQ2.setVisibility(View.VISIBLE);
            }
        });



        // Question 2

        view.findViewById(R.id.post_quiz_q2_cs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setAdmissionCategory(getString(R.string.post_quiz_q2_cs));
                binding.postQuizQ3.setVisibility(View.VISIBLE);
                binding.postQuizSubmit.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q2_stats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setAdmissionCategory(getString(R.string.post_quiz_q2_stats));
                binding.postQuizQ3.setVisibility(View.VISIBLE);
                binding.postQuizSubmit.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q2_math).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setAdmissionCategory(getString(R.string.post_quiz_q2_math));
                binding.postQuizQ3.setVisibility(View.VISIBLE);
                binding.postQuizSubmit.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.post_quiz_q2_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req.setAdmissionCategory(getString(R.string.post_quiz_q2_other));
                binding.postQuizQ3.setVisibility(View.VISIBLE);
                binding.postQuizSubmit.setVisibility(View.VISIBLE);
            }
        });


        // Question 3

        view.findViewById(R.id.post_quiz_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    binding.postQuizQ3Mata31Input.getText()
                    EditText mata31_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_mata31_input);
                    int mata31 = Integer.parseInt(mata31_input.getText().toString());

                    EditText mata67_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_mata67_input);
                    int mata67 = Integer.parseInt(mata67_input.getText().toString());

                    EditText mata22_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_mata22_input);
                    int mata22 = Integer.parseInt(mata22_input.getText().toString());

                    EditText mata37_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_mata37_input);
                    int mata37 = Integer.parseInt(mata37_input.getText().toString());

                    EditText csca08_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_csca08_input);
                    int csca08 = Integer.parseInt(csca08_input.getText().toString());

                    EditText csca48_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_csca48_input);
                    int csca48 = Integer.parseInt(csca48_input.getText().toString());

                    Marks marks = new Marks(mata31, mata67, mata22, mata37, csca08, csca48);
                    req.setMarks(marks);

                    String results = req.calculatePostResult(getContext());

                    post_results.setText(results);

                }
                catch (Exception e){
                    Log.i("An error has occurred:", e.toString());
                }
            }
        });
    }
}
