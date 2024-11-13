
// Define types of items that share actions / properties
enum ItemType {
    Weapon,
    Healing,
    Key,
    Animal,
    Plant,
    Item;

    public static ItemType toType(String s) {
        switch (s) {
            case "Weapon":
                return ItemType.Weapon;

            case "Healing":
                return ItemType.Healing;

            case "Key":
                return ItemType.Key;

            case "Animal":
                return ItemType.Animal;

            case "Plant":
                return ItemType.Plant;

            default:
                return ItemType.Item;
        }
    }
}

// Object defining how general items work in your game
// All other item classes should inherit this class
public class Item {
    String name;
    ItemType type;
    String desc;

    Item(String n, String t, String d) {
        name = n;
        type = ItemType.toType(t);
        desc = d;
    }

    public String inspect() {
        String message = "This is a " + this.name + ", a kind of " + this.type.name() + ". Description: " + this.desc;
        return message;
    }
}
