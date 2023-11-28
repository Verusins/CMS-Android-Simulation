package example.com.cmsandroidsimulation.presenters;

import android.media.metrics.Event;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public abstract class User {
    String firstName = "Ark";
    String lastName = "Conrad";

    // TODO: implement api calls
    public CompletableFuture<ArrayList<EventInfo>> getEvents()
    {
        CompletableFuture<ArrayList<EventInfo>> asynclist = new CompletableFuture<>();
        ArrayList<EventInfo> eventslist = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> task = db.collection("events").get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.i("MASTER APP", "Successful events query");
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        EventInfo eventinfo = new EventInfo(
                                document.getId(),
                                document.getString("author"),
                                document.getString("title"),
                                document.getString("details"),
                                (ArrayList<Double>) document.get("rating"),
                                (Date) document.get("eventStartDateTime"),
                                (Date) document.get("eventEndDateTime"),
                                (ArrayList<EventComment>) document.get("comments")
                        );
                        eventslist.add(eventinfo);
                    }
                    asynclist.complete(eventslist);
                } else {
                    asynclist.completeExceptionally(task.getException());
                }
            }
        });
        return asynclist;
    }

    // TODO: implement api calls
    public CompletableFuture<ArrayList<Announcement>> getAnnouncements()
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return PlaceholderValues.generateTestAnnouncementList();
        });
    }
    public CompletableFuture<Boolean> isAdmin(String email)
    {
        CompletableFuture<Boolean> isAdmin = new CompletableFuture<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> task = db.collection("users").whereEqualTo("email", email).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        isAdmin.complete(document.getBoolean("isAdmin"));
                        return;
                    }
                    isAdmin.complete(false);
                } else {
                    isAdmin.completeExceptionally(task.getException());
                }
            }
        });
        return isAdmin;
    }

}
