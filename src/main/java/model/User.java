package src.main.java.model;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Post> posts = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public List<Post> getPosts() { return posts; }

    public void addPost(Post post) {
        posts.add(post);
    }
}
