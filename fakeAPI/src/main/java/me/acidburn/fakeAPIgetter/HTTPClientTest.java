package me.acidburn.fakeAPIgetter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;

public class HTTPClientTest
{
    private HttpClient client;
    private HttpRequest req;
    private static URI endpoint;
    private HttpResponse<String> resp;

    public HTTPClientTest() throws URISyntaxException, IOException,InterruptedException {
        endpoint = new URI("https://jsonplaceholder.typicode.com/todos");
        client = HttpClient.newHttpClient();
        req = HttpRequest.newBuilder().uri(endpoint).version(HttpClient.Version.HTTP_1_1).GET().build();
        resp = client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    public String getResponse() {
        return resp.body();
    }
    public int getStatusCode() {
        return resp.statusCode();
    }

}
