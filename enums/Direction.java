package summer2022.Battleship.enums;

import summer2022.Battleship.core.Board;
import summer2022.Battleship.core.Location;

import java.util.ArrayList;
import java.util.List;

public enum Direction {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Location getLocationFrom(Location loc, Direction dir, int distance) {

        Board board = loc.getBoard();
        return switch (dir) {
            case UP -> board.getLocation(loc.getRow() - distance, loc.getCol());
            case DOWN -> board.getLocation(loc.getRow() + distance, loc.getCol());
            case LEFT -> board.getLocation(loc.getRow(), loc.getCol() - distance);
            case RIGHT -> board.getLocation(loc.getRow(), loc.getCol() + distance);
        };

    }

    public static List<String> getStringArray() {
        List<String> directions = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            directions.add(direction.name());
        }
        return directions;
    }

    public static List<Integer> getLocationFrom(int row, int col, Direction direction, int distance) {
        List<Integer> coords = new ArrayList<>();
        coords.add(0, row);
        coords.add(1, col);
        switch (direction) {
            case UP -> coords.set(0, row - distance);
            case DOWN -> coords.set(0, row + distance);
            case LEFT -> coords.set(1, col - distance);
            case RIGHT -> coords.set(1, col + distance);
        }
        return coords;
    }

}
