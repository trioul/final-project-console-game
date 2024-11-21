import java.util.*;


public class Game {

    static String name;
    static int choice;
    public LoadYAML yl = new LoadYAML();

    // helper function for printing
    private static void printSlow(String toPrint) {
        char[] chars = toPrint.toCharArray();
        for (int i=0; i < chars.length; i++) {
            System.out.print(chars[i]);
            try { Thread.sleep(25);} 
            catch (InterruptedException e) {Thread.currentThread().interrupt();}
        }
        System.out.println("");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        // only instantiate once
        Scanner myObj = new Scanner(System.in);

        // init game state
        System.out.println("What is your name?");
        name = myObj.nextLine();
        GameState state = new GameState(name);

        // beginning flavor text
        /**
        printSlow("Welcome, "+name+".");
        System.out.println("");
        printSlow("You've been studying in the library for hours and decide to take a break by walking around.");
        System.out.println("");
        printSlow("You go downstairs into the basement, find an archive room, and get distracted by an old book describing the first version of Java (\'The Java Tutorial\' by Mary Campione and Kathy Walrath, published in 1997).");
        System.out.println("");
        printSlow("After reading for a while, you look up and notice that the library looks... different. The lighting seems a little dimmer, the room smells of cigarettes, and you could have sworn the carpet was a different pattern when you first walked into this room.");
        */
        while (!state.finished) {
            System.out.println("What do you want to do next?");
            System.out.println("[1]: Look around the room.");
            System.out.println("[2]: Move to a new room.");
            System.out.println("[3]: Pick up an object from the room.");
            System.out.println("[4]: Use an object from my inventory.");

            choice = myObj.nextInt();

            switch (choice) {
                case 1:

                    //printSlow(state.room.desc);
                    printSlow("You can see the following items:");
                    for (int i=0; i<state.room.contents.length; i++) {
                        printSlow(state.room.contents[i].toString());
                    }

                default:
                    printSlow("Unidentified input, try again?");
            }
        }
        // User interaction
        //Weapon sword = new Weapon("Excalibur", ["Weapon"], "It shiny", 1, 10);
        //System.out.println(sword.inspect());
        //System.out.println("Attack 1: " + sword.attack());
        //System.out.println("Attack 2: " + sword.attack());
        //System.out.println("Attack 3: " + sword.attack());

    }
}
