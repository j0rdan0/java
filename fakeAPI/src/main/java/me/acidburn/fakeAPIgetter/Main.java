package me.acidburn.fakeAPIgetter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.logging.log4j.*;

public class Main {

    static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Working");
        try {
            HTTPClientTest c = new HTTPClientTest();
            System.out.println("Status code: " + c.getStatusCode());
            String r = c.getResponse();
            String sep = " ";

            TodoSerializer ts = new TodoSerializer(r);
            ArrayList<Todo> info = ts.getTodos();
            for (Todo t: info) {
                System.out.println(new StringBuffer().append(t.getId()).append(sep).append(t.getUserId()).append(sep).append(t.getTitle()).append(sep).append(t.isCompleted()));
            }

        } catch (IOException e) {
            logger.debug(e.getMessage());
        } catch (URISyntaxException e) {
            logger.debug(e.getMessage());
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
        }
    }
}

