package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SongService {

    private final List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        validateSong(song);
        songs.add(song);
    }

    public Optional<Song> shortestSong() {
        return songs.stream()
                .min(Comparator.comparingInt(Song::getLength));
    }

    public List<Song> findSongByTitle(String title) {
        return songs.stream()
                .filter(s -> s.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public boolean isPerformerInSong(Song song, String performer) {
        validateSong(song);
        return song.getPerformers().stream()
                .anyMatch(p -> p.equalsIgnoreCase(performer));
    }

    public List<String> titlesBeforeDate(LocalDate endDate) {
        return songs.stream()
                .filter(s -> s.getRelease().isBefore(endDate))
                .map(Song::getTitle)
                .collect(Collectors.toList());
    }

    private void validateSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null!");
        }
    }
}
