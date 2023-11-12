package example.com.cmsandroidsimulation;

import java.util.concurrent.CompletableFuture;

public class Admin extends User{
    private static Admin instance;
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
}