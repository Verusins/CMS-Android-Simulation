package example.com.cmsandroidsimulation;

import java.util.ArrayList;
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
    public CompletableFuture<ArrayList<Complaint>> getComplaints(EventInfo eventInfo, int rating)
    {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate an asynchronous API call
            try {
                Thread.sleep(2000); // Simulating a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return generateTestComplaintList();
        });
    }

    private static ArrayList<Complaint> generateTestComplaintList() {
        ArrayList<Complaint> complaintList = new ArrayList<>();

        // Complaint 1
        Complaint complaint1 = new Complaint("User1", "This is not working properly.");
        complaintList.add(complaint1);

        // Complaint 2
        Complaint complaint2 = new Complaint("User2", "I am facing issues with the system.");
        complaintList.add(complaint2);

        // Add more complaints as needed

        return complaintList;
    }
}