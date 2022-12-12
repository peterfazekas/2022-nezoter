package hu.auditorium.model.domain;

public class Chair {

    private final int row;
    private final int number;
    private final Category category;
    private final boolean occupied;

    public Chair(int row, int number, Category category, boolean occupied) {
        this.row = row;
        this.number = number;
        this.category = category;
        this.occupied = occupied;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public int getCategoryId() {
        return category.getId();
    }

    public int getPrice() {
        return category.getPrice();
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isChair(int row, int number) {
        return this.row == row && this.number == number;
    }

    @Override
    public String toString() {
        return isOccupied() ? "x" : String.valueOf(getCategoryId());
    }
}
