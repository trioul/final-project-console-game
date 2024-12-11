import java.util.Random;
import java.util.List;

public class Weapon extends Item {
    private int minDamage;
    private int maxDamage;
    private Random rn;

    // Constructor to initialize the weapon
    public Weapon(String name, List<String> types, String description, String action, int minDamage, int maxDamage) {
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
            if ("attack".equals(action)) {
                int damage = attack();
                System.out.println("You attack with " + name + " and deal " + damage + " damage.");
                gameState.dealDamage(damage);  // Assuming there's a `dealDamage` method in GameState
            } else {
                System.out.println("You can't use the " + name + " right now.");
            }
            markAsUsed();  // Mark the item as used
        } else {
            System.out.println("The " + name + " has already been used.");
        }
    }
}
