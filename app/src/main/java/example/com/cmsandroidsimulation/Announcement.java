package example.com.cmsandroidsimulation;

public class Announcement {
    private String name = "POSt update!";
    private String description = "We update some POSt requirement, please check the new POSt" +
            " requirements if you are a first year students and pursuing a degree in CMS.\nThe" +
            " CMS family welcomes everyone!";

    public Announcement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
