package summer2022.Battleship.enums;

public enum BoardStatus {

    UNKNOWN('~'),
    MISS('O'),
    HIT('X');

    private final char display;

    BoardStatus(char display) {
        this.display = display;
    }

    public char getDisplay() {
        return display;
    }

}
