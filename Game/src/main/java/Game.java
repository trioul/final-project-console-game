import java.util.*;

public class Game {

    static String name;
    static int choice;
    static String itemp;

    // Helper function for printing slowly
    private static void printSlow(String toPrint) {
        char[] chars = toPrint.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
            try { Thread.sleep(25); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        System.out.println("");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        // Initialize the scanner and game state
        Scanner myObj = new Scanner(System.in);

        System.out.println("What is your name?");
        name = myObj.nextLine();

        // Initialize the game state
        GameState state = new GameState(name);

        // Beginning flavor text (optional)
        printSlow("Welcome, " + name + ".");
        printSlow("You've entered a mysterious world, and you must navigate your way to escape.");

        // Main game loop
        while (!state.finished) {
            System.out.println("");
            System.out.println("What do you want to do next?");
            System.out.println("[1]: Look around the room.");
            System.out.println("[2]: Move to a new room.");
            System.out.println("[3]: Pick up an object from the room.");
            System.out.println("[4]: Examine my inventory.");
            System.out.println("[5]: Use an object from my inventory.");

            choice = myObj.nextInt();
            myObj.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Look around the room
                    printSlow("You can see the following items:");
                    for (Item c : state.room.contents) printSlow(c.name);
                    printSlow("You also notice that this room has doors:");
                    for (String c : state.room.doors.keySet()) printSlow(c);
                    break;

                case 2:
                    // Move to a new room
                    printSlow("Which door?");
                    String door = myObj.nextLine();
                    try {
                        // Check if the door exists
                        if (state.room.doors.containsKey(door)) {
                            String rtemp = state.room.doors.get(door);

                            // Check if the door is locked
                            if (state.room.lockedDoors.containsKey(door) && state.room.lockedDoors.get(door)) {
                                // If locked, try to unlock it
                                printSlow("This door is locked. You need a key to open it.");
                                String keyItemName = "Key"; // Assuming you need a "Key"
                                
                                Item keyItem = null;
                                for(int i =0; i< state.inventory.size(); i++){
                                    if(state.inventory.get(i).toString().equals(keyItemName)){
                                        keyItem = state.inventory.get(i);
                                        break;
                                    }
                                }
                                if (keyItem != null) {
                                    state.room.unlockDoor(door, state);
                                }
                            } else {
                                // Move to the new room
                                state.room = state.rooms.get(rtemp);
                                printSlow("You step through the " + door + " door. You are now in the " + state.room.name + ".");
                            }
                        } else {
                            printSlow("Unknown door.");
                        }
                    } catch (Exception e) {
                        printSlow("Unknown door.");
                    }
                    break;

                case 3:
                    // Pick up an item
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        state.room.contents.remove(item);
                        state.inventory.add(item);
                        printSlow("You pick up the " + item.name + ". " + item.description + ".");
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;

                case 4:
                    // Examine inventory
                    printSlow("Your inventory:");
                    printSlow(state.inventory.toString());
                    break;

                case 5:
                    // Use an item from the inventory
                    printSlow("Which item?");
                    itemp = myObj.nextLine();
                    try {
                        Item item = state.items.get(itemp);
                        if (state.inventory.contains(item)) {
                            item.use(state);  // Pass the GameState to interact with it
                            printSlow(item.use);  // Display action result
                            if (item.action.equals("drop")) {
                                state.inventory.remove(item);
                                state.room.contents.add(item);
                                state.rooms.put(state.room.name, state.room);
                            }
                        } else {
                            printSlow("Unknown item.");
                        }
                    } catch (Exception e) {
                        printSlow("Unknown item.");
                    }
                    break;

                default:
                    printSlow("Unidentified input, try again?");
            }

            // Update game state
            String update = state.update();
            printSlow(update);
        }

        // End the game
        printSlow("You win!");
    }
}
