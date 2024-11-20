import java.util.Random;

public class Animal extends Item {
    int min;
    int max;
    private Random rn;

    public Animal(String name, String[] type, String desc, int min_damage, int max_damage) {
        super(name, type, desc);
        min = min_damage;
        max = max_damage;
        rn = new Random();
    }

    // uniformly distributed random number
    public int attack() {
        int var = min + rn.nextInt((max-min) + 1);
        return var;
    }

}