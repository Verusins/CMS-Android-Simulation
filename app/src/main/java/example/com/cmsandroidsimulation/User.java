package example.com.cmsandroidsimulation;

import android.media.metrics.Event;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public abstract class User {
    String firstName = "Con";
    String lastName = "Rad";

    // TODO: implement api calls
    public CompletableFuture<ArrayList<EventInfo>> getEvents()
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            throw new UnsupportedOperationException();
        });
    }
    private static ArrayList<EventInfo> generateTestEventInfoList() {
        ArrayList<EventInfo> eventInfoList = new ArrayList<>();

        // Event 1
        EventInfo event1 = new EventInfo();
        event1.id = 1;
        event1.title = "School Fair";
        event1.details = "Annual school fair featuring various activities.";
        event1.rating = 4.5f; // Example rating
        event1.comments = generateTestComments();
        eventInfoList.add(event1);

        // Event 2
        EventInfo event2 = new EventInfo();
        event2.id = 2;
        event2.title = "Science Exhibition";
        event2.details = "Showcasing student projects and experiments.";
        event2.rating = 3.8f; // Example rating
        event2.comments = generateTestComments();
        eventInfoList.add(event2);

        // Add more events as needed

        return eventInfoList;
    }


    private static ArrayList<EventComment> generateTestComments() {
        ArrayList<EventComment> comments = new ArrayList<>();

        EventComment comment1 = new EventComment("User1", "Great event!");
        EventComment comment2 = new EventComment("User2", "Enjoyed it a lot!");

        comments.add(comment1);
        comments.add(comment2);

        // Add more comments as needed

        return comments;
    }

}
