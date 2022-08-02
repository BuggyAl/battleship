package summer2022.Battleship.enums;

public enum ShipType {

    SMALL(2),
    MEDIUM(3),
    LARGE(4),
    HUGE(5);

    private int length;

    ShipType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
