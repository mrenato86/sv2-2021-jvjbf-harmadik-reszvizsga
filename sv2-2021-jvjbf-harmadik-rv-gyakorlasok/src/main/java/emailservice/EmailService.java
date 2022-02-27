package emailservice;

import java.util.LinkedHashSet;
import java.util.Set;

public class EmailService {

    private final Set<User> users = new LinkedHashSet<>();

    public void registerUser(String email) {
        if (!isValidEmailAddress(email)) {
            throw new IllegalArgumentException("Email address is not valid: " + email);
        }
        if (!users.add(new User(email))) {
            throw new IllegalArgumentException("Email address is already taken!");
        }
    }

    private boolean isValidEmailAddress(String email) {
        boolean isLowerCase = email.toLowerCase().equals(email);
        int atIndex = email.indexOf('@');
        int dotIndex = email.indexOf('.');
        return isLowerCase && atIndex > 0 && atIndex < email.length() - 1 && dotIndex > 0 && atIndex < dotIndex - 1;
    }

    public void sendEmail(String from, String to, String subject, String content) {
        findUser(from).sendEmail(subject, content, findUser(to));
    }

    private User findUser(String email) {
        return users.stream()
                .filter(u -> u.getEmailAddress().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not Found!"));
    }

    public void sendSpam(String subject, String content) {
        users.forEach(u -> u.getEmail(new Spam(u, subject, content)));
    }

    public Set<User> getUsers() {
        return new LinkedHashSet<>(users);
    }
}
