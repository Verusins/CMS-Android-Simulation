package example.com.cmsandroidsimulation.datastructures;

import java.util.ArrayList;
import java.util.Date;

public class EventInfo extends UserPost{

    // Might change to builder pattern later.

    private final ArrayList<EventComment> comments;
    private final Date eventStartDateTime;
    private final Date eventEndDateTime;
    private final String eventid;

    private final int maxppl;

    private final String location;

    private final ArrayList<String> attendees;

    public EventInfo(String eventid, String author, String title, String details,
                     Date eventStartDateTime, Date eventEndDateTime,
                     ArrayList<EventComment> comments, int maxppl, ArrayList<String> attendees, String location)
    {
        super(author, title, details);
        this.comments = comments;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
        this.eventid = eventid;
        this.maxppl = maxppl;
        this.attendees = attendees;
        this.location = location;
    }

    public String getLocation() {return location;}
    public ArrayList<String> getAttendees(){return attendees;}

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