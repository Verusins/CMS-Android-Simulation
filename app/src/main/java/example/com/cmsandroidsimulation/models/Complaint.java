package example.com.cmsandroidsimulation.models;

import java.util.Date;

public class Complaint{
    private final String username;
    private final String content;
    public Complaint(String username, String content)
    {
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
