package src.main.java.model;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private String id;
    private String title;
    private String content;
    private String author;
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, String author) {
        this.id = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public List<Comment> getComments() { return comments; }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}