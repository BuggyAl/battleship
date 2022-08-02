package summer2022.Battleship.core;

import summer2022.Battleship.enums.Direction;
import summer2022.Battleship.enums.LocationStatus;
import summer2022.Battleship.enums.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private final int row;
    private final int col;

    private final Board board;

    private LocationStatus status;
    private Ship ship;

    public Location(int row, int col, Board board) {
        this.row = row;
        this.col = col;
        this.board = board;
        this.status = LocationStatus.EMPTY;
    }

    public Board getBoard() {
        return board;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        this.status = LocationStatus.ALIVE;
    }

    public Ship getShip() {
        return ship;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public LocationStatus getStatus() {
        return status;
    }

    public void setStatus(LocationStatus status) {
        this.status = status;
    }

    public static boolean isInBounds(List<Integer> coords) {
        return coords.get(0) >= 1 && coords.get(0) <= 10 && coords.get(1) >= 1 && coords.get(1) <= 10;
    }

    public static boolean isInBounds(int row, int col) {
        return row >= 1 && row <= 10 && col >= 1 && col <= 10;
    }

    public static boolean isValidLocation(Board board, int row, int col, ShipType shipType, Direction direction) {
        int length = shipType.getLength();
        for (int i = 1; i <= length; i++) {
            ArrayList<Integer> coords = (ArrayList<Integer>) Direction.getLocationFrom(row, col, direction, i);
            if (!Location.isInBounds(coords)) { return false; }
            if (board.getLocation(coords.get(0), coords.get(1)).getStatus() != LocationStatus.EMPTY) { return false;}
        }
        return true;
    }


}
