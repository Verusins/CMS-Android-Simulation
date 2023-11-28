package example.com.cmsandroidsimulation.presenters;

import android.media.metrics.Event;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public abstract class User {
    protected String email;

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
                        ArrayList<EventComment> eventComments = new ArrayList<EventComment>();
                        for(HashMap<String, Object> obj : (ArrayList<HashMap<String, Object>>)document.get("comments"))
                        {
                            eventComments.add(new EventComment(
                                    (String)obj.get("author"),
                                    (String)obj.get("details"),
                                    ((Long)obj.get("rating")).intValue(),
                                    ((Timestamp)obj.get("date")).toDate()
                            ));
                        }
                        EventInfo eventinfo = new EventInfo(
                            document.getId(),
                            document.getString("author"),
                            document.getString("title"),
                            document.getString("details"),
                            (Date) document.get("eventStartDateTime"),
                            (Date) document.get("eventEndDateTime"),
                            eventComments,
                            document.getDouble("maxppl").intValue(),
                            (ArrayList<String>)document.get("attendees")
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
    public CompletableFuture<String> getName(String email)
    {
        CompletableFuture<String> name = new CompletableFuture<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> task = db.collection("users").whereEqualTo("email", email).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        name.complete(document.getString("name"));
                        return;
                    }
                    name.completeExceptionally(task.getException());
                } else {
                    name.completeExceptionally(task.getException());
                }
            }
        });
        return name;
    }
}
