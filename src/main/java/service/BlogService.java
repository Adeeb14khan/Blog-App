package src.main.java.service;

import src.main.java.model.Post;
import src.main.java.model.Comment;
import src.main.java. util.FileUtil;
import java.util.ArrayList;
import java.util.List;

public class BlogService {
    private List<Post> posts;
    private static final String POSTS_FILE = "posts.dat";
    
    public BlogService() {
        this.posts = loadPosts();
    }
    
    public void addPost(Post post) {
        posts.add(post);
        savePosts();
    }
    
    public List<Post> getAllPosts() {
        return new ArrayList<>(posts); // Return a copy
    }
    
    public void addComment(String postId, String content, String author) {
        posts.stream()
            .filter(post -> post.getId().equals(postId))
            .findFirst()
            .ifPresent(post -> {
                post.addComment(new Comment(content, author));
                savePosts();
            });
    }
    
    @SuppressWarnings("unchecked")
    private List<Post> loadPosts() {
        List<Post> loaded = (List<Post>) FileUtil.loadFromFile(POSTS_FILE);
        return loaded != null ? loaded : new ArrayList<>();
    }
    
    private void savePosts() {
        FileUtil.saveToFile(POSTS_FILE, posts);
    }
}
