package example.com.cmsandroidsimulation.presenters;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import example.com.cmsandroidsimulation.FailedLoginException;
import example.com.cmsandroidsimulation.models.Complaint;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public class Admin extends User{
    private final static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseUser user = null;
    private static String udid;
    private static Admin instance;
    // TODO: implement api calls

    public static Task<AuthResult> Login(String email, String password) throws FailedLoginException
    {
            // Simulate an asynchronous API call
        Task<AuthResult> authResult = mAuth.signInWithEmailAndPassword(email, password);
        authResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    instance = new Admin();
                    instance.email = email;
                    user = mAuth.getCurrentUser();
                } else {

                    Log.e("MASTER APP", "Login failed");
                    // If sign in fails, display a message to the user.
                    // throw new FailedLoginException();
                }
            }
        });

        return authResult;
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
        return instance;
    }
    // TODO: implement api calls
    public CompletableFuture<ArrayList<Complaint>> getComplaints()
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return PlaceholderValues.generateTestComplaintList();
        });
    }

    // TODO: implement API calls
    public Task<DocumentReference> postEvent(String author, String title, String details, Date startDateTime,
                                             Date endDateTime, int maxppl)
    {
        Log.i("MASTER APP", "prepare post event");
        Log.i("MASTER APP", author);
        Log.i("MASTER APP", title);
        Log.i("MASTER APP", details);
        Log.i("MASTER APP", startDateTime + "");
        Log.i("MASTER APP", endDateTime + "");
        Log.i("MASTER APP", maxppl + "");

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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentReference> task = db.collection("events")
                .add(event);
        task.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("SUCCESS", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                });
        return task;
    }

    // TODO: implement API calls
    public Task<DocumentReference> postAnnouncement(String details)
    {
        Map<String, Object> announcement = new HashMap<>();
        announcement.put("details", details);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentReference> task = db.collection("announcements")
                .add(announcement);
        task.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("SUCCESS", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                });
        return task;
    }
}