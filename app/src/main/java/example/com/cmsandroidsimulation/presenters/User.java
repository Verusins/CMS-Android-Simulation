package example.com.cmsandroidsimulation.presenters;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public abstract class User {
    protected static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    protected static FirebaseUser user = null;
    protected String email;
    protected static User instance;

    // TODO: implement api calls
    public static CompletableFuture<User> Login(String email, String password)
    {
        CompletableFuture<User> completableFuture = new CompletableFuture<User>();

        Task<AuthResult> authResult = mAuth.signInWithEmailAndPassword(email, password);
        authResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
//                    instance = new Student();
//                    instance.email = email;
                    user = mAuth.getCurrentUser();
                    CompletableFuture<Boolean> cf = isAdmin(email);
                    cf.thenAccept(
                            (admin) -> {
                                if(admin)
                                    instance = new Admin();
                                else
                                    instance = new Student();
                                instance.email = email;
                                completableFuture.complete(instance);
                            }
                    );
                    cf.exceptionally((e) -> {
                        completableFuture.completeExceptionally(e);
                        return null;
                    });
                } else {
                    completableFuture.completeExceptionally(task.getException());
                }
            }
        });

        return completableFuture;
    }
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
                                ((Timestamp) document.get("startDateTime")).toDate(),
                                ((Timestamp) document.get("endDateTime")).toDate(),
                            eventComments,
                            document.getDouble("maxppl").intValue(),
                            (ArrayList<String>)document.get("attendees"),
                                document.getString("location")
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
        CompletableFuture<ArrayList<Announcement>> asynclist = new CompletableFuture<>();
        ArrayList<Announcement> alist = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> task = db.collection("announcements").get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Announcement announcement = new Announcement(
                                document.getString("author"),
                                document.getString("title"),
                                document.getString("details")
                        );
                        alist.add(announcement);
                    }
                    asynclist.complete(alist);
                } else {
                    asynclist.completeExceptionally(task.getException());
                }
            }
        });
        return asynclist;
    }
    public static CompletableFuture<Boolean> isAdmin(String email)
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

    public String getEmail()
    {
        return email;
    }
}
