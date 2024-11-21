//package Game.src.main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import org.yaml.snakeyaml.Yaml;
import java.util.HashMap;

public class LoadYAML {

    String fname;
    HashMap<String, Object> data;

    public HashMap<String, Object> load(String type) {
        switch (type) {
            case "room":
                // Loader
                //fname = "rooms.yaml";
                //InputStream stream = new FileInputStream(fname);
                //Room room = (new Yaml(new Constructor(Room.class))).load(stream);
                Yaml yaml = new Yaml();
                System.out.println("Loading YAML");
                File file = new File("./config/rooms.yml");
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    data = yaml.load(inputStream);
                } catch (FileNotFoundException e) {System.out.println("Couldn't find file");}
                break;
            default:
                System.out.println("Failed to ID YAML type");
        }
        return data;
    }

    public LoadYAML() {
        data = new HashMap<String, Object>();
    }
}
