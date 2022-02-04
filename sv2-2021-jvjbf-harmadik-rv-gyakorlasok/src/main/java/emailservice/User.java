package emailservice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class User {

    private final String emailAddress;
    private final List<Email> incoming = new ArrayList<>();
    private final List<Email> sent = new ArrayList<>();
    private boolean hasSpamFilter;

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void getEmail(Email email) {
        if (hasSpamFilter && email.getFrom().emailAddress.toLowerCase().contains("spam")) {
            throw new IllegalStateException("Be careful, this is a spam!");
        }
        incoming.add(email);
    }

    public void sendEmail(String subject, String content, User to) {
        Email toSend = new Normal(this, to, subject, content);
        sent.add(toSend);
        to.getEmail(toSend);
    }

    public void spamFilterChange() {
        hasSpamFilter = !hasSpamFilter;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Email> getIncoming() {
        incoming.sort(Comparator.comparing(Email::isImportant).reversed());
        return List.copyOf(incoming);
    }

    public List<Email> getSent() {
        return List.copyOf(sent);
    }

    public boolean hasSpamFilter() {
        return hasSpamFilter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailAddress, user.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }
}
