import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;

public class GameTest {

    @Test
    public void testYAML() {
        LoadYAML yl = new LoadYAML();
        HashMap<String, Object> data = yl.load("room");
        HashMap<String, Object> room1 = (HashMap<String, Object>) data.get("room1");
        assertEquals(room1.get("name"), "Starting Room");
    }

}
