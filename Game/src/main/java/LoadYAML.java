import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.yaml.snakeyaml.Yaml;
import java.util.ArrayList;

public class LoadYAML {

    HashMap<String, Object> data;
    HashMap<String, Room> rooms = new HashMap<>();
    HashMap<String, Item> items = new HashMap<>();

    // Load the YAML file
    private HashMap<String, Object> load(String fname) {
        Yaml yaml = new Yaml();
        File file = new File("./config/" + fname);
        try {
            FileInputStream inputStream = new FileInputStream(file); 
            data = yaml.load(inputStream);
            
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file: " + fname);
        }
        return data;
    }

    // Load rooms from the rooms.yaml file
    public HashMap<String, Room> loadRooms() {
        data = load("rooms.yaml");

        if (data == null) return rooms;

        for (String name : data.keySet()) {
            Map<String, Object> inRoom = (Map<String, Object>) data.get(name);
            List<String> contents = (List<String>) inRoom.get("contents");
            Map<String, String> doors = (Map<String, String>) inRoom.get("doors");

            // Initialize lockedDoors map (default empty if not provided)
            Map<String, Boolean> lockedDoors = new HashMap<>();
            if (inRoom.containsKey("locked")) {
                lockedDoors.put("west", (Boolean) inRoom.get("locked"));
            }

            List<Item> roomContents = new ArrayList<>();
            for (String itemName : contents) {
                Item item = items.get(itemName);
                if (item != null) {
                    roomContents.add(item);
                } else {
                    System.out.println("Item not found: " + itemName);
                }
            }

            rooms.put(name, new Room(name, roomContents, doors, lockedDoors));
        }
        return rooms;
    }

    // Load items from the items.yaml file
    public HashMap<String, Item> loadItems() {
        data = load("items.yaml");
    
        if (data == null) return items;
    
        for (String name : data.keySet()) {
            Map<String, Object> properties = (Map<String, Object>) data.get(name);
            String desc = (String) properties.get("description");
            Map<String, Object> use = (Map<String, Object>) properties.get("use");
            String useText = (String) use.get("text");
            String useAction = (String) use.get("action");
            List<String> types = (List<String>) properties.get("type");
    
            int minDamage = 0;
            int maxDamage = 0;
            if (properties.containsKey("min-damage") && properties.containsKey("max-damage")) {
                minDamage = (Integer) properties.get("min-damage");
                maxDamage = (Integer) properties.get("max-damage");
            }
    
            // Creating Weapon instance properly
            if (types.contains("Weapon")) {
                items.put(name, new Weapon(name, types, desc, useText, minDamage, maxDamage));
            } else if (types.contains("Animal")) {
                items.put(name, new Animal(name, types, desc, useText, minDamage, maxDamage));
            } else {
                items.put(name, new GenericItem(name, types, desc, useText, useAction));  // Default Item
            }
        }
        return items;
    }
    

    // Constructor that loads the items and rooms data
    public LoadYAML() {
        items = loadItems();
        rooms = loadRooms();
    }
}
