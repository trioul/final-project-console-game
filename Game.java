import java.util.*;


public class Game {

    static String name;

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in); // only instantiate once

        // User interaction
        System.out.println("What is your name?");
        name = myObj.nextLine();
        System.out.println("Welcome, "+name+".");


        System.out.println("You've been studying in the library for hours and decide to take a break by walking around.\n");

        System.out.println("You go downstairs into the basement, find an archive room, and get distracted by an old book describing the first version of Java (\'The Java Tutorial\' by Mary Campione and Kathy Walrath, published in 1997).\n");

        System.out.println("After reading for a while, you look up and notice that the library looks... different. The lighting seems a little dimmer, the room smells of cigarettes, and you could have sworn the carpet was a different pattern when you first walked into this room.");

        Weapon sword = new Weapon("Excalibur", "Weapon", "It shiny", 1, 10);
        System.out.println(sword.inspect());
        System.out.println("Attack 1: " + sword.attack());
        System.out.println("Attack 2: " + sword.attack());
        System.out.println("Attack 3: " + sword.attack());

    }
}
