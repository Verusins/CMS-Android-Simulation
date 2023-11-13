package example.com.cmsandroidsimulation.presenters;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.models.Announcement;
import example.com.cmsandroidsimulation.models.Complaint;
import example.com.cmsandroidsimulation.models.EventInfo;
import example.com.cmsandroidsimulation.models.PlaceholderValues;

public class Admin extends User{
    private static Admin instance;
    // TODO: implement api calls
    public static CompletableFuture<Admin> Login(String username, String password)
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Simulated data retrieval
            System.out.println("Admin info fetched from API");

            // throw new FailedLoginException();
            instance = new Admin();
            return instance;
        });
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
    public CompletableFuture<Void> postEvent(String title, String details, Date startDateTime,
                                             Date endDateTime)
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

    // TODO: implement API calls
    public CompletableFuture<Void> postAnnouncement(String details)
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