import com.diogonunes.jcolor.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

            System.out.println(Ansi.colorize("WORKING...", Attribute.RED_TEXT()));
            Location location = new Location().createLocation();
            System.out.println(location.getKey());
    }
}
