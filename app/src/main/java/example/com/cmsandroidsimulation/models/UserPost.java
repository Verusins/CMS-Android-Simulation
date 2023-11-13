package example.com.cmsandroidsimulation.models;

import javax.annotation.Nullable;

public abstract class UserPost {
    private final String author;
    private final @Nullable String title;
    private final String details;

    public UserPost(String author, @Nullable String title, String details)
    {
        this.author = author;
        this.details = details;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDetails() {
        return details;
    }
    @Nullable
    public String getTitle()
    {
        return title;
    }

}
