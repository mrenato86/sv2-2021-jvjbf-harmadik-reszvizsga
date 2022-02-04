package examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ExamService {

    private int theoryMax;
    private int practiceMax;
    private final Map<String, ExamResult> results = new TreeMap<>();

    public void readFromFIle(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            setMaxPoints(br.readLine());
            while (br.ready()) {
                String[] parts = br.readLine().split(";");
                results.put(parts[0], parseExamResult(parts[1]));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: " + ioe.getMessage(), ioe);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Error in file: " + e.getMessage(), e);
        }
    }

    private ExamResult parseExamResult(String line) {
        String[] parts = line.split(" ");
        return new ExamResult(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    private void setMaxPoints(String line) {
        ExamResult maxPoints = parseExamResult(line);
        theoryMax = maxPoints.getTheory();
        practiceMax = maxPoints.getPractice();
    }

    public List<String> findPeopleFailed() {
        return results.entrySet().stream()
                .filter(e -> !e.getValue().isTheoryPassed(theoryMax) || !e.getValue().isPracticePassed(practiceMax))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public String findBestPerson() {
        return results.entrySet().stream()
                .filter(e -> e.getValue().isPracticePassed(practiceMax) && e.getValue().isTheoryPassed(theoryMax))
                .max(Comparator.comparingInt(e -> e.getValue().getSumOfPoints()))
                .orElseThrow(() -> new IllegalStateException("Not Found!"))
                .getKey();
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public Map<String, ExamResult> getResults() {
        return results;
    }
}
