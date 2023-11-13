package example.com.cmsandroidsimulation.models;

import example.com.cmsandroidsimulation.presenters.User;

public class EventComment extends UserPost {
    public EventComment(String author, String details) {
        super(author, null, details);
    }
}
