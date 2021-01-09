package me.acidburn.fakeAPIgetter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class TestingMain {
    public static void main(String[] args) {
        System.out.println("Working");
        try {
            HTTPClientTest c = new HTTPClientTest();
            System.out.println("Status code: " + c.getStatusCode());
          //  System.out.println(c.getResponse());

            String r = c.getResponse();
            String sep = " ";

            TodoSerializer ts = new TodoSerializer(r);
            ArrayList<Todo> info = ts.getTodos();
            for (Todo t: info) {
                System.out.println(new StringBuffer().append(t.getId()).append(sep).append(t.getUserId()).append(sep).append(t.getTitle()).append(sep).append(t.isCompleted()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

