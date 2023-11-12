package example.com.cmsandroidsimulation.models;

public class EventComment {
    private final String username;
    private final String content;

    public EventComment(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }
}
