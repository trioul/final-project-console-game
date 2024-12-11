import java.util.Random;
import java.util.List;

public class Animal extends Item {
    private int minDamage;
    private int maxDamage;
    private Random rn;

    // Constructor to initialize an animal with specific attributes
    public Animal(String name, List<String> types, String description, String action, int minDamage, int maxDamage) {
        super(name, types, description, action, action);  // Passing action to the parent constructor
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.rn = new Random();
    }

    // Attack method: returns a random damage value within the range [min, max]
    public int attack() {
        return minDamage + rn.nextInt((maxDamage - minDamage) + 1);  // Random damage between min and max
    }

    // Overriding the 'use' method from the Item class
    @Override
    public void use(GameState gameState) {
        if (!used) {
            // Perform the action (e.g., animal attacks)
            if ("attack".equals(action)) {
                int damage = attack();
                System.out.println(name + " attacks with damage: " + damage);
                gameState.dealDamage(damage);  // Assuming GameState has dealDamage method
            } else {
                System.out.println(name + " cannot do anything right now.");
            }
            markAsUsed();  // Mark the item as used
        } else {
            System.out.println(name + " has already been used.");
        }
    }
}
