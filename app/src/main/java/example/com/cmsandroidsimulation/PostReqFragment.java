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
                    EditText mata67_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_mata67_input);
                    EditText mata22_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_mata22_input);
                    EditText mata37_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_mata37_input);
                    EditText csca08_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_csca08_input);
                    EditText csca48_input = (EditText) view.getRootView().findViewById(R.id.post_quiz_q3_csca48_input);

                    if (mata31_input.getText().toString().equals("")
                            || mata67_input.getText().toString().equals("")
                            || mata22_input.getText().toString().equals("")
                            || mata37_input.getText().toString().equals("")
                            || csca08_input.getText().toString().equals("")
                            || csca48_input.getText().toString().equals("")){

                        Toast myToast = Toast.makeText(getActivity(), getContext().getString(R.string.NaN_input_error), Toast.LENGTH_SHORT);
                        myToast.show();
                    }
                    else {
                        int mata31 = Integer.parseInt(mata31_input.getText().toString());
                        int mata67 = Integer.parseInt(mata67_input.getText().toString());
                        int mata22 = Integer.parseInt(mata22_input.getText().toString());
                        int mata37 = Integer.parseInt(mata37_input.getText().toString());
                        int csca08 = Integer.parseInt(csca08_input.getText().toString());
                        int csca48 = Integer.parseInt(csca48_input.getText().toString());

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

                            String results = req.calculatePostResult(getContext());

                            post_results.setText(results);

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

                req.setTargetPost(null);
                req.setAdmissionCategory(null);
                req.setMarks(null);
            }
        });
    }
}
