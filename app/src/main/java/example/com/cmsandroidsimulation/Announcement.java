package example.com.cmsandroidsimulation;

public class Announcement {
    private String title = "POSt update!";
    private String description = "We update some POSt requirement, please check the new POSt" +
            " requirements if you are a first year students and pursuing a degree in CMS.\nThe" +
            " CMS family welcomes everyone!";

    public Announcement() {}

    public Announcement(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
