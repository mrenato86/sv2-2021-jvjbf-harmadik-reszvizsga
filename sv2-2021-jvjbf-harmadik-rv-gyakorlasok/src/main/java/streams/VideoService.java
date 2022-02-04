package streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VideoService {

    private final List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        if (video != null) {
            videos.add(video);
        }
    }

    public List<Video> findVideosByTitle(String titlePart) {
        return videos.stream()
                .filter(v -> v.getTitle().contains(titlePart))
                .sorted(Comparator.comparing(Video::getUploadDate).reversed())
                .collect(Collectors.toList());
    }

    public long countVideosWithHashTag(String hashTag) {
        return videos.stream()
                .filter(v -> v.getHashTags().contains(hashTag))
                .count();
    }

    public Video firstVideoByDate() {
        return videos.stream()
                .min(Comparator.comparing(Video::getUploadDate))
                .orElseThrow(() -> new IllegalStateException("No videos in list!"));
    }

    public long sumOfVideoLengths() {
        return videos.stream()
                .mapToInt(Video::getLength)
                .sum();
    }
}
