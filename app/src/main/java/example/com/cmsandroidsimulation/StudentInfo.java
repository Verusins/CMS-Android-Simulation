package example.com.cmsandroidsimulation;

import java.util.concurrent.CompletableFuture;

public class StudentInfo{
    private static StudentInfo instance;
    String firstName = "Con";
    String lastName = "Rad";

    public static CompletableFuture<StudentInfo> Login(String username, String password)
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

            return new StudentInfo();
        });
    }
}