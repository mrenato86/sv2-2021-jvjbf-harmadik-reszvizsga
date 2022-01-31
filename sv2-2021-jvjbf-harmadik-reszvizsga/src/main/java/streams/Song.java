package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Song {

    private String title;
    private int length;
    private List<String> performers;
    private LocalDate release;

    public Song(String title, int length, List<String> performers, LocalDate release) {
        validateParameters(title, length, performers, release);
        this.title = title;
        this.length = length;
        this.performers = new ArrayList<>(performers);
        this.release = release;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public List<String> getPerformers() {
        return new ArrayList<>(performers);
    }

    public LocalDate getRelease() {
        return release;
    }

    private void validateParameters(String title, int length, List<String> performers, LocalDate release) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty!");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Length cannot be negative!");
        }
        if (performers == null) {
            throw new IllegalArgumentException("Performers list cannot be null!");
        }
        if (release == null) {
            throw new IllegalArgumentException("Release date cannot be null!");
        }
    }
}
