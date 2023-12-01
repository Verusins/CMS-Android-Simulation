package example.com.cmsandroidsimulation.presenters;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.FailedLoginException;
import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.Complaint;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public class Admin extends User{
    // TODO: implement api calls

    public void Logout(){

        FirebaseAuth.getInstance().signOut();
        instance = null;
    }
    public static Task<AuthResult> Register(String username, String email, String password) throws FailedLoginException
    {
        // Simulate an asynchronous API call
        Task<AuthResult> authResult = mAuth.createUserWithEmailAndPassword(email, password);
        authResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    instance = new Admin();
                    instance.email = email;
                    user = mAuth.getCurrentUser();
                    ArrayList<String> events = new ArrayList<>();
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", username);
                    user.put("email", email);
                    user.put("isAdmin", true);
                    user.put("events", events);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.i("MASTER APP", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            });
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("MASTER APP", "Register failed");
                }
            }
        });
        return authResult;
    }
    public static Admin getInstance()
    {
        return (Admin) instance;
    }
    // TODO: implement api calls
    public CompletableFuture<ArrayList<Complaint>> getComplaints()
    {
        CompletableFuture<ArrayList<Complaint>> asynclist = new CompletableFuture<>();
        ArrayList<Complaint> clist = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> task = db.collection("complaints").get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Complaint complaint = new Complaint(
                                document.getString("username"),
                                document.getString("content")
                        );
                        clist.add(complaint);
                    }
                    asynclist.complete(clist);
                } else {
                    asynclist.completeExceptionally(task.getException());
                }
            }
        });
        return asynclist;
    }

    public Task<DocumentReference> postEvent(String author, String title, String details, Date startDateTime,
                                             Date endDateTime, int maxppl, String location)
    {
        ArrayList<String> attendees = new ArrayList<>();
        ArrayList<EventComment> comments = new ArrayList<>();
        Map<String, Object> event = new HashMap<>();
        event.put("author", author);
        event.put("title", title);
        event.put("details", details);
        event.put("startDateTime", startDateTime);
        event.put("endDateTime", endDateTime);
        event.put("comments", comments);
        event.put("maxppl", maxppl);
        event.put("attendees", attendees);
        event.put("location", location);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentReference> task = db.collection("events")
                .add(event);
        task.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("MASTER APP", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                });
        return task;
    }
    public CompletableFuture<Void> postAnnouncement(String title, String details)
    {
        Map<String, Object> announcement = new HashMap<>();
        announcement.put("author", getName(email));
        announcement.put("title", title);
        announcement.put("details", details);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentReference> task = db.collection("announcements")
                .add(announcement);
        task.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("MASTER APP", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                });
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<ArrayList<Complaint>> getComplaint()
    {
        CompletableFuture<ArrayList<Complaint>> asynclist = new CompletableFuture<>();
        ArrayList<Complaint> complist = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> task = db.collection("complaint").get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.i("MASTER APP", "Successful complaint query");
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Complaint compl = new Complaint(
                                document.getString("username"),
                                document.getString("description"));
                        complist.add(compl);
                    }
                    asynclist.complete(complist);
                } else {
                    asynclist.completeExceptionally(task.getException());
                }
            }
        });
        return asynclist;
    }

}