package example.com.cmsandroidsimulation.models;

import java.util.ArrayList;
import java.util.Date;

public class EventInfo {

    // Might change to builder pattern later.

    private final int id;
    private final String author;
    private final String title;
    private final String details;
    private final float rating;
    private final ArrayList<EventComment> comments;
    private final Date eventStartDateTime;
    private final Date eventEndDateTime;

    public EventInfo(int id, String author, String title, String details, float rating,
                     Date eventStartDateTime, Date eventEndDateTime,
                     ArrayList<EventComment> comments)
    {
        this.id = id;
        this.author = author;
        this.title = title;
        this.details = details;
        this.rating = rating;
        this.comments = comments;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
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

    public Date getEventEndDateTime() {
        return eventEndDateTime;
    }

    public Date getEventStartDateTime() {
        return eventStartDateTime;
    }


    public String getAuthor() {
        return author;
    }
}