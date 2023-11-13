package example.com.cmsandroidsimulation.models;

import java.util.Date;

public class Announcement {
    private final String author;
    private final String title;
    private final String details;
    public Announcement(String author, String title, String details)
    {
        this.author = author;
        this.details = details;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }
}
