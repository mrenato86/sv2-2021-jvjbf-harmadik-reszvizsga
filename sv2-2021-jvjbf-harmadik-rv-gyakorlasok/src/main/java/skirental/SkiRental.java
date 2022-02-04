package skirental;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SkiRental {

    private final Map<String, Equipment> rentals = new TreeMap<>();

    public void loadFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
//        ------Basic version:-----------------------------------------
//            br.readLine();
//            while (br.ready()) {
//                String[] parts = br.readLine().split(";");
//                rentals.put(parts[0], parseEquipment(parts[1]));
//            }
//        ------Stream version 1:--------------------------------------
//            rentals = br.lines()
//                    .skip(1)
//                    .map(l -> l.split(";"))
//                    .collect(Collectors.toMap(
//                            v -> v[0],
//                            v -> parseEquipment(v[1]),
//                            (e1, e2) -> e2,
//                            TreeMap::new));
            br.lines()
                    .skip(1)
                    .map(l -> l.split(";"))
                    .forEach(v -> rentals.put(v[0], parseEquipment(v[1])));
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + ioe.getMessage(), ioe);
        }
    }

    private Equipment parseEquipment(String line) {
        String[] parts = line.split(" ");
        return new Equipment(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public List<String> listChildren() {
        return rentals.entrySet().stream()
                .filter(e -> e.getValue().isChildSize())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public String getNameOfPeopleWithBiggestFoot() {
        return rentals.entrySet().stream()
                .filter(e -> e.getValue().isBothRented())
                .max(Comparator.comparingInt(e -> e.getValue().getSizeOfBoot()))
                .orElseThrow(() -> new IllegalStateException("Not Found!"))
                .getKey();
    }

    public Map<String, Equipment> getRentals() {
        return Map.copyOf(rentals);
    }
}
