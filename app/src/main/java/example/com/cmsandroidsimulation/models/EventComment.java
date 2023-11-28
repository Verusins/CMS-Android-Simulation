package example.com.cmsandroidsimulation.models;

import java.util.Date;

import example.com.cmsandroidsimulation.presenters.User;

public class EventComment extends UserPost {
    private final int rating;
    private final Date date;
    public EventComment(String author, String details, int rating, Date date) {
        super(author, null, details);
        this.rating = rating;
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }

}
