package example.com.cmsandroidsimulation.models;

import java.util.ArrayList;
import java.util.Date;

public class EventInfo extends UserPost{

    // Might change to builder pattern later.

    private final ArrayList<EventComment> comments;
    private final Date eventStartDateTime;
    private final Date eventEndDateTime;
    private final String eventid;

    private final int maxppl;

    public EventInfo(String eventid, String author, String title, String details,
                     Date eventStartDateTime, Date eventEndDateTime,
                     ArrayList<EventComment> comments, int maxppl)
    {
        super(author, title, details);
        this.comments = comments;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
        this.eventid = eventid;
        this.maxppl = maxppl;
    }


    public String getEventid(){
        return eventid;
    }

    public ArrayList<EventComment> getComments() {
        return comments;
    }

    public Date getEventEndDateTime() {
        return eventEndDateTime;
    }

    public int getMaxppl() {return maxppl;}

    public Date getEventStartDateTime() {
        return eventStartDateTime;
    }
}