package example.com.cmsandroidsimulation.presenters;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.EventComment;
import example.com.cmsandroidsimulation.models.EventInfo;

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

            return generateTestEventInfoList();
        });
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

            throw new UnsupportedOperationException();
        });
    }
    private static ArrayList<EventInfo> generateTestEventInfoList() {
        ArrayList<EventInfo> eventInfoList = new ArrayList<>();

        // Event 1
        EventInfo event1 = new EventInfo(
                1,
                "School Fair",
                "Annual school fair featuring various activities.",
                4.5f,
                generateTestComments());
        eventInfoList.add(event1);

        EventInfo event2 = new EventInfo(
                2,
                "Science Exhibition",
                "Showcasing student projects and experiments.",
                3.8f,
                generateTestComments());
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
