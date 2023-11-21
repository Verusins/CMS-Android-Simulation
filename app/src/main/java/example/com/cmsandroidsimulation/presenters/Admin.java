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
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public class Admin extends User{
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseUser user = null;
    private static String udid;
    private static String admin_username;
    private static String admin_email;
    private static Admin instance;
    // TODO: implement api calls

    public static Task<AuthResult> Login(String email, String username, String password) throws FailedLoginException
    {
            // Simulate an asynchronous API call
        Task<AuthResult> authResult = mAuth.signInWithEmailAndPassword(username, password);
        authResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    instance = new Admin();
                    user = mAuth.getCurrentUser();
                    admin_username = username;
                    admin_email = email;
                } else {
                    // If sign in fails, display a message to the user.
                    throw new FailedLoginException();
                }
            }
        });

        return authResult;
    }
    public static Task<AuthResult> SignUp(String username, String email, String password) throws FailedLoginException
    {
        // Simulate an asynchronous API call
        Task<AuthResult> authResult = mAuth.createUserWithEmailAndPassword(email, password);
        authResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    instance = new Admin();
                    user = mAuth.getCurrentUser();
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", username);
                    user.put("email", email);
                    user.put("isAdmin", true);
                    admin_username = username;
                    admin_email = email;
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("LOGIN SUCCESS", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            });
                } else {
                    // If sign in fails, display a message to the user.
                    throw new FailedLoginException();
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
    public FirebaseFirestore postEvent(String title, String details, Date startDateTime,
                                             Date endDateTime)
    {
        Map<String, Object> event = new HashMap<>();
        event.put("title", title);
        event.put("details", details);
        event.put("startDateTime", startDateTime);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("events")
                .add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("ADDED EVENT", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                });
        return db;
    }

    // TODO: implement API calls
    public FirebaseFirestore postAnnouncement(String details)
    {
        Map<String, Object> announcement = new HashMap<>();
        announcement.put("details", details);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("announcements")
                .add(announcement)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("ADDED EVENT", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                });
        return db;
    }
}