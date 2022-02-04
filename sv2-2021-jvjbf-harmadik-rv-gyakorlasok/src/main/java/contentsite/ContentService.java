package contentsite;

import java.util.HashSet;
import java.util.Set;

public class ContentService {

    private final Set<User> allUsers = new HashSet<>();
    private final Set<Content> allContent = new HashSet<>();

    public void registerUser(String name, String password) {
        if (!allUsers.add(new User(name, password))) {
            throw new IllegalArgumentException("Username is already taken: " + name);
        }
    }

    public void addContent(Content content) {
        if (!allContent.add(content)) {
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }
    }

    public void logIn(String username, String password) {
        User user = findUser(username);
        user.setLogIn(checkPassword(user, password));
    }

    private User findUser(String username) {
        return allUsers.stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Username is wrong!"));
    }

    private boolean checkPassword(User user, String password) {
        if (user.getPassword() == (user.getUserName() + password).hashCode()) {
            return true;
        }
        throw new IllegalArgumentException("Password is Invalid!");
    }

    public void clickOnContent(User user, Content content) {
        if (!user.isLogIn()) {
            throw new IllegalStateException("Log in to watch this content!");
        }
        if (!user.isPremiumMember() && content.isPremiumContent()) {
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        }
        content.click(user);
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(allUsers);
    }

    public Set<Content> getAllContent() {
        return new HashSet<>(allContent);
    }
}
