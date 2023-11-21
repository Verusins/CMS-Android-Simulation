package example.com.cmsandroidsimulation.models;


import java.util.ArrayList;
import java.util.Date;

public class PlaceholderValues {

    public static ArrayList<EventInfo> generateTestEventInfoList() {
        ArrayList<EventInfo> eventInfoList = new ArrayList<>();

        // Event 1
        EventInfo event1 = new EventInfo(
                1,
                "School Fair",
                "Annual school fair featuring various activities.",
                "Poo poo poo poo poo poo poo poo poo poo poo poo poo ",
                4.5f,
                new Date(),
                new Date(),
                generateTestComments());
        eventInfoList.add(event1);

        EventInfo event2 = new EventInfo(
                2,
                "Science Exhibition",
                "Showcasing student projects and experiments.",
                "Poo poo poo poo poo poo poo poo poo poo poo poo poo 2 ",
                3.8f,
                new Date(),
                new Date(),
                generateTestComments());
        eventInfoList.add(event2);

        // Add more events as needed

        return eventInfoList;
    }

    public static EventInfo generateTestEventInfoSingle() {
        EventInfo event = new EventInfo(
                1,
                "School Fair",
                "Annual school fair featuring various activities.",
                "Poo poo poo poo poo poo poo poo poo poo poo poo poo ",
                4.5f,
                new Date(),
                new Date(),
                generateTestComments());

        return event;
    }


    public static ArrayList<EventComment> generateTestComments() {
        ArrayList<EventComment> comments = new ArrayList<>();

        EventComment comment1 = new EventComment("User1", "Great event!");
        EventComment comment2 = new EventComment("User2", "Enjoyed it a lot!");

        comments.add(comment1);
        comments.add(comment2);

        // Add more comments as needed

        return comments;
    }

    public static ArrayList<Announcement> generateTestAnnouncementList() {
        ArrayList<Announcement> announcementList = new ArrayList<>();

        // Announcement 1
        Announcement announcement1 = new Announcement("John Doe", "Important announcement!",
                "We are eating donuts.");
        announcementList.add(announcement1);

        // Announcement 2
        Announcement announcement2 = new Announcement("Jane Smith", "Reminder: Event tomorrow.",
                "We are kicking random people.");
        announcementList.add(announcement2);

        // Add more announcements as needed

        return announcementList;
    }


    public static ArrayList<Complaint> generateTestComplaintList() {
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
