package src.main.java.service;
import src.main.java.model.User;
import src.main.java.util.FileUtil;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users;
    private static final String USERS_FILE = "users.dat";

    public AuthService() {
        this.users = loadUsers();
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password));
        saveUsers();
        return true;
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    @SuppressWarnings("unchecked")
    private Map<String, User> loadUsers() {
        Map<String, User> loaded = (Map<String, User>) FileUtil.loadFromFile(USERS_FILE);
        return loaded != null ? loaded : new HashMap<>();
    }

    private void saveUsers() {
        FileUtil.saveToFile(USERS_FILE, users);
    }
}