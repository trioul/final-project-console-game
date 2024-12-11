import java.util.List;

public abstract class Item {
    protected String name;
    protected String description;
    protected List<String> types;
    protected String use;
    protected String action;
    protected boolean used = false;  // To track if the item has been used

    // Constructor for initializing item
    public Item(String name, List<String> types, String description, String use, String action) {
        this.name = name;
        this.types = types;
        this.description = description;
        this.use = use;
        this.action = action;
    }

    // Getter methods for item name and description
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    // Abstract method for using the item, to be implemented in subclasses
    public abstract void use(GameState gameState);

    // Optional method to check if the item has been used
    public boolean isUsed() {
        return this.used;
    }

    // Optional setter to mark the item as used
    public void markAsUsed() {
        this.used = true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
