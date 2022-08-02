package summer2022.Battleship;

import summer2022.Battleship.core.Board;
import summer2022.Battleship.core.Computer;
import summer2022.Battleship.core.Location;
import summer2022.Battleship.core.Ship;
import summer2022.Battleship.enums.Direction;
import summer2022.Battleship.enums.LocationStatus;
import summer2022.Battleship.enums.ShipType;

import java.util.Random;
import java.util.Scanner;

public class Battleship {

    public static Board gameBoard = new Board();
    public static Computer computer;
    static Scanner scanner = new Scanner(System.in);

    static Random random = new Random();

    public static final ShipType[] shipsNeeded = {ShipType.SMALL, ShipType.MEDIUM, ShipType.MEDIUM, ShipType.LARGE, ShipType.HUGE};

    public static void playGame() {

        loadNewBoard();

        for (ShipType shipType : shipsNeeded) {
            System.out.println();
            gameBoard.printShips();
            createShip(shipType);
        }

        gameBoard.printShips();

        computer = new Computer();
        computer.loadComputerBoard();

        while(true) {

            playerTurn();
            if (computer.getBoard().allShipsSunk()) {
                finishGame(true);
                break;
            }

            computerTurn();
            if (gameBoard.allShipsSunk()) {
                finishGame(false);
                break;
            }
        }

    }

    public static void playerTurn() {
        Board opponentBoard = computer.getBoard();
        System.out.println("\nComputer's Board");
        opponentBoard.printForOpponent();
        while (true) {
            System.out.print("\nEnter a row to attack: ");
            int row = scanner.nextInt();
            System.out.print("Enter a column to attack: ");
            int col = scanner.nextInt();
            if (!Location.isInBounds(row, col)) {
                System.out.println("That guess it out of bounds!");
            } else {
                Location loc = opponentBoard.getLocation(row, col);
                if (loc.getStatus() == LocationStatus.EMPTY) {
                    System.out.println("\nYou missed!");
                    opponentBoard.attackAt(row, col);
                    break;
                } else if (loc.getStatus() == LocationStatus.ALIVE) {
                    System.out.println("\nHit!");
                    opponentBoard.attackAt(row, col);
                    if (loc.getShip().isSunk()) { System.out.println("\nYou sunk the computer's " + loc.getShip().getType() + " ship!"); }
                    break;
                } else {
                    System.out.println("\nYou already attacked this location!");
                }
            }
        }
    }

    public static void computerTurn() {
        System.out.println("\nYour Board");
        gameBoard.printForOpponent();
        while (true) {
            // pick a random location to attack
            int row = random.nextInt(10) + 1;
            int col = random.nextInt(10) + 1;

            Location loc = gameBoard.getLocation(row, col);
            if (loc.getStatus() == LocationStatus.EMPTY) {
                System.out.println("\nComputer missed!");
                gameBoard.attackAt(row, col);
                break;
            } else if (loc.getStatus() == LocationStatus.ALIVE) {
                System.out.println("\nComputer hit!");
                gameBoard.attackAt(row, col);
                if (loc.getShip().isSunk()) {
                    System.out.println("\nComputer sunk your " + loc.getShip().getType() + " ship!");
                }
                break;
            }
        }
    }

    public static void finishGame(boolean playerIsWinner) {
        if (playerIsWinner) {
            System.out.println("\nYou won!");
        } else {
            System.out.println("\nYou lost!");
        }

        System.out.print("Would you like to play again? (y/n): ");
        String answer = scanner.next();
        if (answer.equals("y")) {
            playGame();
        } else {
            System.out.println("\nThanks for playing!");
        }

    }

    private static void createShip(ShipType shipType) {
        while (true) {
            System.out.print("\nPlease enter the row of your " + shipType + " ship: ");
            int row = scanner.nextInt();
            System.out.print("Please enter the column of your " + shipType + " ship: ");
            int col = scanner.nextInt();
            System.out.print("Please enter the direction of your " + shipType + " ship: ");
            String direction = scanner.next();
            if (!Direction.getStringArray().contains(direction.toUpperCase())) {
                System.out.println("Invalid direction!");
            } else {
                Direction dir = Direction.valueOf(direction.toUpperCase());
                if (Location.isValidLocation(gameBoard, row, col, shipType, dir)) {
                    gameBoard.addShip(new Ship(gameBoard.getLocation(row, col), shipType, dir));
                    break;
                } else {
                    System.out.println("Your ship cannot go there!");
                }
            }
        }
    }

    public static void loadNewBoard() {
        gameBoard = new Board();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        playGame();
    }

}
