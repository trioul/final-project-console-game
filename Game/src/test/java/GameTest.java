import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        Room room1 = yl.rooms.get("Starting Room");
        assertEquals(room1.name, "Starting Room");
    }

    // Test if using a Weapon decreases the player's health
    @Test
    public void testWeaponUsage() {
        // Set up a GameState and load items
        GameState gameState = new GameState("Test Player");

        // Simulate a Weapon being in the inventory
        Weapon sword = new Weapon("Sword", null, "A sharp sword", "attack", 5, 10);
        gameState.inventory.add(sword);
        int initialHealth = gameState.getPlayerHealth();

        // Use the sword and expect the player's health to decrease
        sword.use(gameState);

        // Health should be less than the initial value
        assertTrue(gameState.getPlayerHealth() < initialHealth);
    }

    // Test if using an Animal decreases the player's health
    @Test
    public void testAnimalUsage() {
        // Set up a GameState and load items
        GameState gameState = new GameState("Test Player");

        // Simulate an Animal being in the inventory
        Animal frog = new Animal("Poison Frog", null, "A poisonous frog", "attack", 3, 7);
        gameState.inventory.add(frog);
        int initialHealth = gameState.getPlayerHealth();

        // Use the frog and expect the player's health to decrease
        frog.use(gameState);

        // Health should be less than the initial value
        assertTrue(gameState.getPlayerHealth() < initialHealth);
    }
}
