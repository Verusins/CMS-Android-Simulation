package example.com.cmsandroidsimulation.presenters;

import java.util.concurrent.CompletableFuture;

import example.com.cmsandroidsimulation.models.EventInfo;

public class Student extends User {
    private static Student instance;

    // TODO: implement api calls
    public static CompletableFuture<Student> Login(String username, String password)
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Simulated data retrieval
            System.out.println("Student info fetched from API");

            // throw new FailedLoginException();
            instance = new Student();
            return instance;
        });
    }
    public static Student getInstance()
    {
        return instance;
    }

    // TODO: implement api calls
    public CompletableFuture<Void> postEventComment(EventInfo eventInfo, String content)
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
    public CompletableFuture<Void> postEventRating(EventInfo eventInfo, int rating)
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