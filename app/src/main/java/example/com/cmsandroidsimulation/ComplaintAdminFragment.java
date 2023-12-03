package example.com.cmsandroidsimulation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import example.com.cmsandroidsimulation.databinding.ComplaintAdminBinding;
import example.com.cmsandroidsimulation.datastructures.Complaint;
import example.com.cmsandroidsimulation.apiwrapper.Admin;

public class ComplaintAdminFragment extends Fragment {
    private ComplaintAdminBinding binding;
    ComplaintAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ComplaintAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Admin.getInstance().getComplaint().thenAccept((ArrayList<Complaint> complist) -> {
            Log.i("MASTER APP", "complaint: " + complist);
            RecyclerView recyclerView = binding.RecyclerView;
            adapter = new ComplaintAdapter(complist);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
}