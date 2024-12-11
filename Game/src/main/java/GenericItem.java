import java.util.List;

public class GenericItem extends Item{

    public GenericItem(String name, List<String> types, String description, String use, String action){
        super(name, types, description, use, action);
    }
    public void use(GameState gameState){
        System.out.print("You use the " + name + ": " + use);
        if("drop".equals(action)){
            gameState.inventory.remove(this);
            gameState.inventory.add(this);
            System.out.println("You dropped the " +name + " in the room.");
        }
    }
}