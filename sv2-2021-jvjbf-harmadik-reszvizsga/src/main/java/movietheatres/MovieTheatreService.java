package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MovieTheatreService {

    private final Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public void readFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                String theatre = parts[0];
                Movie movie = parseMovie(parts[1]);
                shows.putIfAbsent(theatre, new ArrayList<>());
                shows.get(theatre).add(movie);
            }
        } catch (IOException | DateTimeParseException e) {
            throw new IllegalStateException("Error during file read: " + e.getMessage(), e);
        }

    }

    private Movie parseMovie(String line) {
        String[] parts = line.split(";");
        String title = parts[0];
        LocalTime time = LocalTime.parse(parts[1]);
        return new Movie(title, time);
    }

    public Map<String, List<Movie>> getShows() {
        for (List<Movie> show : shows.values()) {
            show.sort(Comparator.comparing(Movie::getStartTime));
        }
        return new LinkedHashMap<>(shows);
    }

    public List<String> findMovie(String title) {
        Movie searched = new Movie(title, LocalTime.now());
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<Movie>> entry : shows.entrySet()) {
            if (entry.getValue().contains(searched)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public LocalTime findLatestShow(String title) {
        return shows.values().stream()
                .flatMap(Collection::stream)
                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                .map(Movie::getStartTime)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("No result!"));
    }
}
