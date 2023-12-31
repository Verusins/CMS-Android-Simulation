package example.com.cmsandroidsimulation.datastructures;


import java.util.ArrayList;
import java.util.Date;

public class PlaceholderValues {

//    public static ArrayList<EventInfo> generateTestEventInfoList() {
//        ArrayList<EventInfo> eventInfoList = new ArrayList<>();
//
//        // Event 1
//        EventInfo event1 = new EventInfo(
//                "1",
//                "Some Admin",
//                "School Fair",
//                "Annual school fair featuring various activities.",
//                new ArrayList<Double>(Arrays.asList(4.0, 5.0, 1.0)),
//                new Date(),
//                new Date(),
//                generateTestComments());
//        eventInfoList.add(event1);
//
//        EventInfo event2 = new EventInfo(
//                "2",
//                "Some Admin 2",
//                "Science Exhibition",
//                "Showcasing student projects and experiments.",
//                new ArrayList<Double>(Arrays.asList(2.0, 4.0, 3.0)),
//                new Date(),
//                new Date(),
//                generateTestComments());
//        eventInfoList.add(event2);
//
//        // Add more events as needed
//
//        return eventInfoList;
//    }
//
//    public static EventInfo generateTestEventInfoSingle() {
//        EventInfo event = new EventInfo(
//                "1",
//                "School Fair",
//                "Annual school fair featuring various activities.",
//                "Poo poo poo poo poo poo poo poo poo poo poo poo poo ",
//                new ArrayList<Double>(Arrays.asList(4.0, 5.0, 1.0)),
//                new Date(),
//                new Date(),
//                generateTestComments());
//
//        return event;
//    }


    public static ArrayList<EventComment> generateTestComments() {
        ArrayList<EventComment> comments = new ArrayList<>();

        EventComment comment1 = new EventComment("User1", "Great event!", 3, new Date());
        EventComment comment2 = new EventComment("User2", "Enjoyed it a lot!", 2, new Date());
        EventComment comment3 = new EventComment("User3", "Enjoyed it a lot! :3", 5, new Date());

        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);

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
