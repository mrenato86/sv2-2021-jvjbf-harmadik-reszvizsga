package contentsite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Video implements Content {

    private static final int PREMIUM_LENGTH = 15;

    private String title;
    private int length;
    private final List<User> clickedBy = new ArrayList<>();

    public Video(String title, int length) {
        this.title = title;
        this.length = length;
    }

    @Override
    public boolean isPremiumContent() {
        return length > PREMIUM_LENGTH;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(clickedBy);
    }

    @Override
    public void click(User user) {
        if (user != null) {
            clickedBy.add(user);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(title, video.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
