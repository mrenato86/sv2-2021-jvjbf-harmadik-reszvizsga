package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Video {

    private String title;
    private int length;
    private LocalDate uploadDate;
    private final List<String> hashTags;

    public Video(String title, int length, LocalDate uploadDate, List<String> hashTags) {
        this.title = title;
        this.length = length;
        this.uploadDate = uploadDate;
        this.hashTags = new ArrayList<>(hashTags);
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public List<String> getHashTags() {
        return new ArrayList<>(hashTags);
    }
}
