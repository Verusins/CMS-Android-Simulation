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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.FailedLoginException;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;

public class Student extends User {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseUser user = null;
    private static String student_username;
    private static String student_email;
    private static Student instance;

    // TODO: implement api calls
    public static Task<AuthResult> Login(String email, String password)
    {
        Task<AuthResult> authResult = mAuth.signInWithEmailAndPassword(email, password);
        authResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    instance = new Student();
                    user = mAuth.getCurrentUser();
                    // student_username = "";
                    student_email = email;
                } else {
                    // If sign in fails, display a message to the user.
                    throw new FailedLoginException();
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
                    instance = new Student();
                    user = mAuth.getCurrentUser();
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", username);
                    user.put("email", email);
                    user.put("isAdmin", false);
                    student_username = username;
                    student_email = email;
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
    public static Student getInstance()
    {
        return instance;
    }

    // TODO: implement api calls
    public CompletableFuture<Void> postEventComment(EventInfo eventInfo, String content)
    {
        String eventid = eventInfo.getEventid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("events").document(eventid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                ArrayList<EventComment> temp = (ArrayList<EventComment>) document.get("comments");
                                EventComment eventComment = new EventComment(student_username, content);
                                temp.add(eventComment);
                                DocumentReference eventref = db.collection("events").document(eventid);
                                eventref.update("comments", temp);
                            } else {
                                Log.d("BRUH", "No such document");
                            }
                        } else {
                            Log.e("ERROR", "Error getting document: ", task.getException());
                        }
                    }
                });
        return CompletableFuture.completedFuture(null);
    }
    // TODO: implement api calls
    public CompletableFuture<Void> postEventRating(EventInfo eventInfo, int rating)
    {
        String eventid = eventInfo.getEventid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("events").document(eventid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                ArrayList<Double> temp = (ArrayList<Double>) document.get("rating");
                                temp.add((double) rating);
                                DocumentReference eventref = db.collection("events").document(eventid);
                                eventref.update("rating", temp);
                            } else {
                                Log.d("BRUH", "No such document");
                            }
                        } else {
                            Log.e("ERROR", "Error getting document: ", task.getException());
                        }
                    }
                });
        return CompletableFuture.completedFuture(null);
    }
    // TODO: implement api calls
    public CompletableFuture<Boolean> getEventHasRated(EventInfo eventInfo)
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    // TODO: implement api calls
    public CompletableFuture<Void> submitEventRSVP(EventInfo eventInfo)
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
    // TODO: implement api calls
    public CompletableFuture<Boolean> getEventHasRSVPd(EventInfo eventInfo)
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    // TODO: implement api calls
    public CompletableFuture<Void> postComplaint(String content)
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

}