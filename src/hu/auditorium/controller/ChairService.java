package hu.auditorium.controller;

import hu.auditorium.model.domain.Chair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChairService {

    private static final int ROW_MAX = 15;
    private static final int NUMBER_MAX = 20;

    private final List<Chair> chairs;

    public ChairService(List<Chair> chairs) {
        this.chairs = chairs;
    }

    /**
     * 2. feladat
     */
    public String isGivenChairOccupied(int row, int number) {
        return String.format("A megadott szék %s!",
                isOccupied(row, number) ? "már foglalt" : "még üres");
    }

    private boolean isOccupied(int row, int number) {
        return chairs.stream()
                .filter(chair -> chair.isChair(row, number))
                .findAny()
                .map(Chair::isOccupied)
                .orElse(true);
    }

    /**
     * 3. feladat
     */
    public String getStatistic() {
        long occupiedChairs = countOccupiedChairs();
        double percent = occupiedChairs * 100.0 / (ROW_MAX * NUMBER_MAX);
        return String.format("Az előadásra eddig %d jegyet adtak el, ez a nézőtér %.0f%%-a.",
                occupiedChairs, percent);
    }

    private long countOccupiedChairs() {
        return chairs.stream()
                .filter(Chair::isOccupied)
                .count();
    }

    /**
     * 4. feladat
     */
    public int getMostPopularCategoryId() {
        return getChairCategoryMap().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
    }

    private Map<Integer, Long> getChairCategoryMap() {
        return chairs.stream()
                .filter(Chair::isOccupied)
                .collect(Collectors.groupingBy(
                        Chair::getCategoryId,
                        Collectors.counting()));
    }

    /**
     * 5. feladat
     */
    public int countTotalIncome() {
        return chairs.stream()
                .filter(Chair::isOccupied)
                .mapToInt(Chair::getPrice)
                .sum();
    }

    /**
     * 6. feladat
     */

    public long countSingleFreeChairs() {
        return chairs.stream()
                .filter(this::isSingleFreeChair)
                .count();
    }

    private boolean isSingleFreeChair(Chair chair) {
        return !chair.isOccupied() &&
                isOccupied(chair.getRow(), chair.getNumber() - 1) &&
                isOccupied(chair.getRow(), chair.getNumber() + 1);
    }

    /**
     * 7. feldat
     */
    public List<String> getAuditoriumStatus() {
        String auditoriumStatusInRow = getAuditoriumStatusInRow();
        return IntStream.range(0, ROW_MAX)
                .mapToObj(row -> printChairsInRow(auditoriumStatusInRow, row))
                .collect(Collectors.toList());
    }

    private String getAuditoriumStatusInRow() {
        return chairs.stream()
                .map(Chair::toString)
                .collect(Collectors.joining());
    }

    private String printChairsInRow(String auditoriumStatusInRow, int row) {
        return auditoriumStatusInRow.substring(
                row * NUMBER_MAX, (row + 1) * NUMBER_MAX);
    }

}
