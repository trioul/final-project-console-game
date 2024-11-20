//package Game.src.main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import org.yaml.snakeyaml.Yaml;
import java.util.Map;

public class LoadYAML {

    String fname;
    //T data;

    public LoadYAML(String type) {

        switch (type) {
            case "room":
                // Loader
                //fname = "rooms.yaml";
                //InputStream stream = new FileInputStream(fname);
                //Room room = (new Yaml(new Constructor(Room.class))).load(stream);
                File dir = new File(".");
                File[] filesList = dir.listFiles();
                for (File file : filesList) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
                System.out.println("Working Directory = " + System.getProperty("user.dir"));
                Yaml yaml = new Yaml();
                System.out.println("Loading YAML");
                File file = new File("./config/rooms.yml");
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    Map<String, Object> obj = yaml.load(inputStream);
                    System.out.println(obj);
                } catch (FileNotFoundException e) {System.out.println("Couldn't find file");}
            default:
                System.out.println("Failed to ID YAML type");
        }
    }
    
}
