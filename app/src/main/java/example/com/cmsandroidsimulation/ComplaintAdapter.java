package example.com.cmsandroidsimulation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import example.com.cmsandroidsimulation.datastructures.Complaint;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder>{
    private ArrayList<Complaint> complist;

    public ComplaintAdapter(ArrayList<Complaint> complist) {
        this.complist = complist;
//        this.context = context;
    }
    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_input, parent, false);
        return new ComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        Complaint compl = complist.get(position);
        holder.Username.setText(compl.getAuthor());
        holder.Description.setText(compl.getDetails());
        holder.closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Complaint data = complist.get(holder.getAdapterPosition());
                CollectionReference dataCollection = db.collection("complaint");
                dataCollection.whereEqualTo("username", data.getAuthor())
                        .whereEqualTo("description", data.getDetails())
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    document.getReference().delete().addOnSuccessListener(aVoid -> {
                                        // Delete item from RecyclerView
                                        int adapterPosition = holder.getAdapterPosition();
                                        if (adapterPosition != RecyclerView.NO_POSITION) {
                                            complist.remove(adapterPosition);
                                            notifyItemRemoved(adapterPosition);
                                        }
                                    });
                                }
                            }
                        });
            }
        });
    }



    @Override
    public int getItemCount() {
        return complist.size();
    }


    public static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        TextView Username;
        TextView Description;
        Button closebutton;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            Username = itemView.findViewById(R.id.UTORid);
            Description = itemView.findViewById(R.id.complaintTextView);
            closebutton=itemView.findViewById(R.id.closeComplaintButton);
        }
    }
}
