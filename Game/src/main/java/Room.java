import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Room {
    public String name;
    public List<Item> contents;
    public Map<String, String> doors;
    public Map<String, Boolean> lockedDoors;

    // Constructor to initialize the room with contents, doors, and locked doors (optional)
    public Room(String name, List<Item> contents, Map<String, String> doors, Map<String, Boolean> lockedDoors) {
        this.name = name;
        this.contents = contents;
        this.doors = doors;

        // If lockedDoors is not passed, initialize it as an empty map
        if (lockedDoors == null) {
            this.lockedDoors = new HashMap<>();
        } else {
            this.lockedDoors = lockedDoors;
        }
    }

    // Method to unlock a door if the correct condition (e.g., key) is met
    public boolean unlockDoor(String doorName, GameState gameState) {
        if (lockedDoors.containsKey(doorName) && lockedDoors.get(doorName)) {
            // Check if player has the key in their inventory
            if (gameState.inventory.contains(gameState.items.get("Key"))) {  // assuming the item name is "Key"
                lockedDoors.put(doorName, false);  // Unlock the door
                System.out.println("The door is now unlocked!");
                return true;
            } else {
                System.out.println("You need a key to unlock this door.");
                return false;
            }
        }
        return false;  // Door is not locked or doesn't exist
    }

    @Override
    public String toString() {
        return name;
    }
}
