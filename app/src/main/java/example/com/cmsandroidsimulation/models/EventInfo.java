package example.com.cmsandroidsimulation.models;

import java.util.ArrayList;

public class EventInfo {

    // Might change to builder pattern later.

    private final int id;
    private final String title;
    private final String details;
    private final float rating;
    private final ArrayList<EventComment> comments;

    public EventInfo(int id, String title, String details, float rating,
                     ArrayList<EventComment> comments)
    {
        this.id = id;
        this.title = title;
        this.details = details;
        this.rating = rating;
        this.comments = comments;
    }
    public EventInfo(int id, String title, String details)
    {
        this.id = id;
        this.title = title;
        this.details = details;
        this.rating = -1;
        this.comments = null;
    }

    public int getId() {
        return id;
    }

    public float getRating() {
        return rating;
    }

    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<EventComment> getComments() {
        return comments;
    }
}