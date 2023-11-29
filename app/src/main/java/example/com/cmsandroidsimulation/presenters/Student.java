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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.FailedLoginException;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;

public class Student extends User {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseUser user = null;
    private static Student instance;

    public static Task<AuthResult> Login(String email, String password)
    {
        Task<AuthResult> authResult = mAuth.signInWithEmailAndPassword(email, password);
        authResult.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    instance = new Student();
                    instance.email = email;
                    user = mAuth.getCurrentUser();
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
                    instance.email = email;
                    user = mAuth.getCurrentUser();
                    Map<String, Object> user = new HashMap<>();
                    ArrayList<String> events = new ArrayList<>();
                    user.put("name", username);
                    user.put("email", email);
                    user.put("isAdmin", false);
                    user.put("events", events);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("MASTER APP", "DocumentSnapshot added with ID: " + documentReference.getId());
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
    public CompletableFuture<Void> postEventComment(EventInfo eventInfo, String content, int rating)
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
                                getName(email).thenAccept((String username) -> {
                                    ArrayList<EventComment> temp = (ArrayList<EventComment>) document.get("comments");
                                    EventComment eventComment = new EventComment(username, content, rating, new Date());
                                    temp.add(eventComment);
                                    DocumentReference eventref = db.collection("events").document(eventid);
                                    eventref.update("comments", temp);
                                });
                            } else {
                                Log.e("MASTER APP", "No such document");
                            }
                        } else {
                            Log.e("MASTER APP", "Error getting document: ", task.getException());
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
    public CompletableFuture<Void> setEventHasRSVPd(EventInfo eventInfo, boolean setTrue)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> task = db.collection("users").whereEqualTo("email", email).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ArrayList<String> events = (ArrayList<String>) document.get("events");
                        if (!setTrue && events.indexOf(eventInfo.getEventid()) != -1){
                            events.remove(eventInfo.getEventid());
                        }
                        else if (setTrue && events.indexOf(eventInfo.getEventid()) == -1){
                            events.add(eventInfo.getEventid());
                        }
                        DocumentReference eventref = db.collection("users").document(document.getId());
                        eventref.update("events", events);
                    }
//                    Log.e("MASTER APP", "No such document USER");
                } else {
                    Log.e("MASTER APP", "Error getting document: ", task.getException());
                }
            }
        });
        Task<QuerySnapshot> eventtask = db.collection("events").get();
        eventtask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> eventtask) {
                if (eventtask.isSuccessful()) {
                    for (QueryDocumentSnapshot document : eventtask.getResult()) {
                        Log.i("MASTERP APP", document.getId() + " " + eventInfo.getEventid());
                        if (document.getId().equals(eventInfo.getEventid())){
                            ArrayList<String> attendees = (ArrayList<String>) document.get("attendees");
                            if (!setTrue && attendees.indexOf(email) != -1){
                                attendees.remove(email);
                            }
                            else if (setTrue && attendees.indexOf(email) == -1){
                                attendees.add(email);
                            }
                            DocumentReference eventref = db.collection("events").document(eventInfo.getEventid());
                            eventref.update("attendees", attendees);
                        }
                    }
//                    Log.e("MASTER APP", "No such document EVENTS");
                } else {
                    Log.e("MASTER APP", "Error getting document: ", eventtask.getException());
                }
            }
        });
        return CompletableFuture.completedFuture(null);
    }

    // TODO: implement api calls
    public Task<DocumentReference> postComplaint(String content)
    {
        Map<String, Object> complaint = new HashMap<>();
        complaint.put("username", getName(email));
        complaint.put("content", content);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentReference> task = db.collection("complaints")
                .add(complaint);
        task.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("MASTER APP", "DocumentSnapshot added with ID: " + documentReference.getId());
            }
        });
        return task;
    }

}