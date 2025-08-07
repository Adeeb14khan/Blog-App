package src.main.java;

import src.main.java.model.Post;
import src.main.java.model.Comment;
import src.main.java.service.AuthService;
import src.main.java.service.BlogService;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static AuthService authService = new AuthService();
    private static BlogService blogService = new BlogService();
    private static Scanner scanner = new Scanner(System.in);
    private static String currentUser = null;

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showAuthMenu() {
        System.out.println("\n=== BlogMaster ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid option!");
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.register(username, password)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists!");
        }
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.login(username, password)) {
            currentUser = username;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== Welcome " + currentUser + " ===");
        System.out.println("1. Create Post");
        System.out.println("2. View All Posts");
        System.out.println("3. Add Comment");
        System.out.println("4. Logout");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createPost();
                break;
            case 2:
                viewPosts();
                break;
            case 3:
                addComment();
                break;
            case 4:
                currentUser = null;
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    private static void createPost() {
        System.out.print("Enter post title: ");
        String title = scanner.nextLine();
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();

        Post post = new Post(title, content, currentUser);
        blogService.addPost(post);
        System.out.println("Post created successfully!");
    }

    private static void viewPosts() {
        System.out.println("\n=== All Posts ===");
        blogService.getAllPosts().forEach(post -> {
            System.out.println("\nID: " + post.getId());
            System.out.println("Title: " + post.getTitle());
            System.out.println("Author: " + post.getAuthor());
            System.out.println("Content: " + post.getContent());
            
            System.out.println("\nComments:");
            if (post.getComments().isEmpty()) {
                System.out.println("No comments yet");
            } else {
                post.getComments().forEach(comment -> 
                    System.out.println(" - " + comment));
            }
            
            System.out.println("-------------------");
        });
    }

    private static void addComment() {
        System.out.print("Enter post ID to comment on: ");
        String postId = scanner.nextLine();
        System.out.print("Enter your comment: ");
        String content = scanner.nextLine();
        
        blogService.addComment(postId, content, currentUser);
        System.out.println("Comment added successfully!");
    }
}