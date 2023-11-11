package example.com.cmsandroidsimulation;

import java.util.concurrent.CompletableFuture;

public class Student {
    private static Student instance;
    String firstName = "Con";
    String lastName = "Rad";

    public static CompletableFuture<Student> Login(String username, String password) {
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

            return new Student();
        });
    }

    public static Student GetInstance()
    {
        return instance;
    }
}