package Model;

import java.time.LocalDate;

/**
 *
 * @author carlo
 */
public class Post {
    private int id;
    private String content;
    private LocalDate postDate;
    private int userId;

    
    public Post(int id, String content, LocalDate postDate, int userId) {
        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.userId = userId;
    }

    
    public Post(String content, LocalDate postDate, int userId) {
        this.content = content;
        this.postDate = postDate;
        this.userId = userId;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
