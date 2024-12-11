import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class GameState {
    String name;
    boolean finished;
    Room room;
    List<Item> inventory = new ArrayList<>();
    Map<String, Room> rooms;
    Map<String, Item> items;

    // Player's health starts at 100
    private int playerHealth = 100;

    // Constructor to initialize the game state
    public GameState(String name) {
        this.name = name;
        finished = false;
        
        // Load rooms and items (assuming LoadYAML is working properly)
        LoadYAML yl = new LoadYAML();
        rooms = yl.rooms;
        items = yl.items;
        
        // Initialize with the starting room
        room = rooms.get("Starting Room");
        
        if (room == null) {
            System.out.println("Starting room not found!");
            finished = true;
        }
    }

    // Getter method for player health
    public int getPlayerHealth() {
        return playerHealth;
    }

    // Method to reduce health when taking damage
    public void dealDamage(int damage) {
        playerHealth -= damage;
        System.out.println("You took " + damage + " damage. Your health is now " + playerHealth + ".");

        // Check if player is dead
        if (playerHealth <= 0) {
            finished = true;
            System.out.println("You have died. Game over.");
        }
    }

    // Method to heal the player
    public void heal(int amount) {
        playerHealth += amount;
        if (playerHealth > 100) {
            playerHealth = 100;  // Max health is 100
        }
        System.out.println("You healed " + amount + " health. Your health is now " + playerHealth + ".");
    }

    // Method to check if the player is alive
    public boolean isAlive() {
        return playerHealth > 0;
    }

    // Method to update the game state and check for the win condition
    public String update() {
        if (room == null) {
            return "Error: Current room is not initialized.";
        }

        // Example win condition: Check if the room contains both "poison frog" and "book"
        if (room.contents.contains(items.get("poison frog")) &&
            room.contents.contains(items.get("book"))) {
            finished = true;
            return "You win! You've completed the game.";
        }

        return "Keep going!";
    }
    

    // Method to handle moving to another room
    public void moveToRoom(String door) {
        if (room == null) {
            System.out.println("Error: Current room is not initialized.");
            return;
        }
    
        // Check if the door exists in the current room
        if (room.doors.containsKey(door)) {
            String roomName = room.doors.get(door);
            Room newRoom = rooms.get(roomName);
    
            if (newRoom != null) {
                // Check if the door is leading to the "Secret Room"
                System.out.println(("Secret Room".equals(newRoom.name)));
                if ("Secret Room".equals(newRoom.name)) {
                    // Check if the "secretkey" is in the player's inventory
                    Item keyItem = null;
                    for(int i =0; i< inventory.size(); i++){
                        if(inventory.get(i).toString().equals("secretKey")){
                            keyItem = inventory.get(i);
                            break;
                        }
                    }
                    if(keyItem != null){
                        System.out.println("The room is locked, but you have the key! You can enter.");
                        room = newRoom;  // Move to the new room
                        System.out.println("You step through the " + door + " door and find yourself in the " + room.name + ".");
                    } else {
                        System.out.println("This room is locked. You need the secret key to enter.");
                    }
                    return;  // Exit the method after handling the "Secret Room"
                }
    
                // If the door is not leading to the "Secret Room", just move to it
                room = newRoom;
                System.out.println("You step through the " + door + " door and find yourself in the " + room.name + ".");
            } else {
                System.out.println("Error: The room \"" + roomName + "\" does not exist.");
            }
        } else {
            System.out.println("Error: There is no door named \"" + door + "\" in this room.");
        }
    }
    
    
    
    

    // Method to handle picking up an item
    public void pickUpItem(String itemName) {
        Item item = items.get(itemName);
        if (item != null && room.contents.contains(item)) {
            room.contents.remove(item);
            inventory.add(item);
            System.out.println("You picked up the " + item.name + ".");
        } else {
            System.out.println("Error: Item \"" + itemName + "\" not found in this room.");
        }
    }

    // Method to handle using an item
    public void useItem(String itemName) {
        Item item = items.get(itemName);
        if (item != null && inventory.contains(item)) {
            item.use(this);  // Assuming items have a use() method
        } else {
            System.out.println("Error: Item \"" + itemName + "\" not found in your inventory.");
        }
    }

    // Method to handle examining the inventory
    public void examineInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory contains: ");
            for (Item item : inventory) {
                System.out.println(item.name + ": " + item.description);
            }
        }
    }

    // Method to inspect the current room and see its contents
    public void lookAroundRoom() {
        System.out.println("You are in the " + room.name + " room.");
        if (!room.contents.isEmpty()) {
            System.out.println("You can see the following items:");
            for (Item item : room.contents) {
                System.out.println(item.name + ": " + item.description);
            }
        } else {
            System.out.println("The room has no items.");
        }

        if (!room.doors.isEmpty()) {
            System.out.println("You notice the following doors:");
            for (String door : room.doors.keySet()) {
                if (door.equals("locked") && "true".equals(room.doors.get(door))) {
                    System.out.println(door + " (locked)");
                } else {
                    System.out.println(door);
                }
            }
        } else {
            System.out.println("This room has no doors.");
        }
    }
}
