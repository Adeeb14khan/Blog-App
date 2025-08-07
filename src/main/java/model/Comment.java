package src.main.java.model;
public class Comment {
    private String id;
    private String content;
    private String author;
    
    public Comment(String content, String author) {
        this.id = java.util.UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
    }
    
    // Getters
    public String getId() { return id; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    
    @Override
    public String toString() {
        return author + ": " + content;
    }
}
