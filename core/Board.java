package summer2022.Battleship.core;

import summer2022.Battleship.enums.LocationStatus;

public class Board {

    private Location[][] board;

    public Board() {
        board = new Location[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Location(i + 1, j + 1, this);
            }
        }
    }

    public void addShip(Ship ship) {
        for (Location location : ship.getLocations()) {
            location.setShip(ship);
        }
    }

    public Location getLocation(int row, int col) {
        if (row < 1 || row > 10 || col < 1 || col > 10) {
            throw new IllegalArgumentException("Location is out of bounds!");
        } else {
            return board[row - 1][col - 1];
        }
    }

    public boolean allShipsSunk() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j].getStatus() == LocationStatus.ALIVE) {
                    return false;
                }
            }
        }
        return true;
    }

    public void attackAt(int row, int col) {
        Location location = getLocation(row, col);
        switch (location.getStatus()) {
            case EMPTY -> location.setStatus(LocationStatus.MISS);
            case ALIVE -> location.setStatus(LocationStatus.HIT);
        }
    }

    public void printShips() {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            if (i + 1 < 10) {
                System.out.print(" " + (i + 1) + " ");
            } else {
                System.out.print(i + 1 + " ");
            }
            for (int j = 0; j < 10; j++) {
                if (board[i][j].getShip() != null) {
                    System.out.print("X ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }

    public void printForOpponent() {
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            if (i + 1 < 10) {
                System.out.print(" " + (i + 1) + " ");
            } else {
                System.out.print(i + 1 + " ");
            }
            for (int j = 0; j < 10; j++) {
                LocationStatus status = board[i][j].getStatus();
                if (status == LocationStatus.EMPTY || status == LocationStatus.ALIVE) {
                    System.out.print("~ ");
                } else if (board[i][j].getStatus() == LocationStatus.MISS) {
                    System.out.print("  ");
                } else if (board[i][j].getStatus() == LocationStatus.HIT) {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

}
