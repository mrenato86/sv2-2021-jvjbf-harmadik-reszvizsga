package contentsite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Podcast implements Content {

    private final String title;
    private final List<String> speakers;
    private final List<User> clickedBy = new ArrayList<>();

    public Podcast(String title, List<String> speakers) {
        this.title = title;
        this.speakers = new ArrayList<>(speakers);
    }

    public List<String> getSpeakers() {
        return new ArrayList<>(speakers);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isPremiumContent() {
        return false;
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
        Podcast podcast = (Podcast) o;
        return Objects.equals(title, podcast.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
