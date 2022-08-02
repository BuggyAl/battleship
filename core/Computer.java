package summer2022.Battleship.core;

import summer2022.Battleship.Battleship;
import summer2022.Battleship.enums.Direction;
import summer2022.Battleship.enums.ShipType;

import java.util.Random;

public class Computer {

    private Board board;

    static Random random = new Random();

    public void loadComputerBoard() {
        System.out.println("\nComputer is generating...");

        board = new Board();

        // randomly place ships on the board
        for (ShipType shipType : Battleship.shipsNeeded) {
            while (true) {
                int row = random.nextInt(10) + 1;
                int col = random.nextInt(10) + 1;
                Direction dir = Direction.values()[random.nextInt(3)];
                if (Location.isValidLocation(board, row, col, shipType, dir)) {
                    board.addShip(new Ship(board.getLocation(row, col), shipType, dir));
                    break;
                }
            }
        }

        System.out.println("\nComputer has generated a board.");

    }

    public Board getBoard() {
        return board;
    }

}
