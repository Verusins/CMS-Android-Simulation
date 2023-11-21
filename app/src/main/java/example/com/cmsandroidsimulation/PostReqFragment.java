package example.com.cmsandroidsimulation;

import android.graphics.Color;
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

    private TextView post_results_desc;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPostreqBinding.inflate(inflater, container, false);
        post_results = binding.postQuizResultsBanner;
        post_results_desc = binding.postQuizResultsDesc;
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

                    if (binding.postQuizQ3Mata31Input.getText().toString().equals("")
                            || binding.postQuizQ3Mata67Input.getText().toString().equals("")
                            || binding.postQuizQ3Mata22Input.getText().toString().equals("")
                            || binding.postQuizQ3Mata37Input.getText().toString().equals("")
                            || binding.postQuizQ3Csca08Input.getText().toString().equals("")
                            || binding.postQuizQ3Csca48Input.getText().toString().equals("")){

                        Toast myToast = Toast.makeText(getActivity(), getContext().getString(R.string.NaN_input_error), Toast.LENGTH_SHORT);
                        myToast.show();
                    }
                    else {
                        int mata31 = Integer.parseInt(binding.postQuizQ3Mata31Input.getText().toString());
                        int mata67 = Integer.parseInt(binding.postQuizQ3Mata67Input.getText().toString());
                        int mata22 = Integer.parseInt(binding.postQuizQ3Mata22Input.getText().toString());
                        int mata37 = Integer.parseInt(binding.postQuizQ3Mata37Input.getText().toString());
                        int csca08 = Integer.parseInt(binding.postQuizQ3Csca08Input.getText().toString());
                        int csca48 = Integer.parseInt(binding.postQuizQ3Csca48Input.getText().toString());

                        if (!(0 <= mata31 && mata31 <= 100
                                && 0 <= mata67 && mata67 <= 100
                                && 0 <= mata22 && mata22 <= 100
                                && 0 <= mata37 && mata37 <= 100
                                && 0 <= csca08 && csca08 <= 100
                                && 0 <= csca48 && csca48 <= 100))
                        {
                            Toast myToast = Toast.makeText(getActivity(), getContext().getString(R.string.mark_input_out_of_bounds), Toast.LENGTH_SHORT);
                            myToast.show();
                        }
                        else {

                            Marks marks = new Marks(mata31, mata67, mata22, mata37, csca08, csca48);

                            req.setMarks(marks);

                            Log.i("check", marks.toString());

                            String[] results = req.calculatePostResult(getContext());

                            if (results[0].equals(getContext().getString(R.string.post_results_pass))){
                                post_results.setBackgroundColor(getContext().getColor(R.color.accept));
                            }else if (results[0].equals(getContext().getString(R.string.post_results_conditional_pass))){
                                post_results.setBackgroundColor(getContext().getColor(R.color.yellow));
                            }else{
                                post_results.setBackgroundColor(getContext().getColor(R.color.warning));
                            }

                            post_results.setText(results[0]);
                            post_results_desc.setText(results[1]);

                            binding.postQuizTitle.setVisibility(View.GONE);

                            binding.postQuizQ1.setVisibility(View.GONE);
                            binding.postQuizQ2.setVisibility(View.GONE);
                            binding.postQuizQ3.setVisibility(View.GONE);

                            binding.postQuizSubmit.setVisibility(View.GONE);
                            binding.postQuizResults.setVisibility(View.VISIBLE);
                        }
                    }


                }
                catch (Exception e){
                    Log.i("An error has occurred:", e.toString());
                }
            }
        });

        view.findViewById(R.id.retake_post_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.postQuizTitle.setVisibility(View.VISIBLE);
                binding.postQuizQ1.setVisibility(View.VISIBLE);
                binding.postQuizResults.setVisibility(View.GONE);


                binding.postQuizQ1Options.clearCheck();
                binding.postQuizQ2Options.clearCheck();

                binding.postQuizQ3Mata31Input.setText(null);
                binding.postQuizQ3Mata67Input.setText(null);
                binding.postQuizQ3Mata22Input.setText(null);
                binding.postQuizQ3Mata37Input.setText(null);
                binding.postQuizQ3Csca08.setText(null);
                binding.postQuizQ3Csca08.setText(null);

                req.setTargetPost(null);
                req.setAdmissionCategory(null);
                req.setMarks(null);
            }
        });
    }
}
