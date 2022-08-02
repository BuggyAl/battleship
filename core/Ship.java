package summer2022.Battleship.core;

import summer2022.Battleship.core.Board;
import summer2022.Battleship.core.Location;
import summer2022.Battleship.enums.Direction;
import summer2022.Battleship.enums.LocationStatus;
import summer2022.Battleship.enums.ShipType;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final Location origin;
    private final ShipType type;
    private final Direction direction;

    List<Location> locations;

    public Ship(Location origin, ShipType type, Direction direction) {
        this.origin = origin;
        this.type = type;
        this.direction = direction;
        this.locations = new ArrayList<>();
        for (int i = 0; i < type.getLength(); i++) {
            // location checks are done in the createShip method (Battleship.java)
            locations.add(Direction.getLocationFrom(origin, direction, i));
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    public ShipType getType() {
        return type;
    }


    public boolean isSunk() {
        for (Location location : locations) {
            if (location.getStatus() != LocationStatus.HIT) { return false; }
        }
        return true;
    }

}
