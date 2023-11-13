package example.com.cmsandroidsimulation.models;

public class EventComment {
    private final String author;
    private final String details;

    public EventComment(String username, String content) {
        this.author = username;
        this.details = content;
    }

    public String getDetails() {
        return details;
    }

    public String getAuthor() {
        return author;
    }
}
