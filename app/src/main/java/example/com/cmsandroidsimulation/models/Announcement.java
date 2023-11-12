package example.com.cmsandroidsimulation.models;

import androidx.annotation.Nullable;

public class Announcement {
    private final String announcer;
    private final String content;
    public Announcement(String announcer, String content)
    {
        this.announcer = announcer;
        this.content = content;
    }

    public String getAnnouncer() {
        return announcer;
    }
    public String getContent() {
        return content;
    }
}
