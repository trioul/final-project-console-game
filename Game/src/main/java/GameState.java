import java.util.HashMap;


public class GameState {
    HashMap<Room, Boolean> visited = new HashMap<Room, Boolean>();
    String name;
    boolean finished;
    //Inventory inv;

    public GameState(String name) {
        this.name = name;
        finished = false;

    }
}
