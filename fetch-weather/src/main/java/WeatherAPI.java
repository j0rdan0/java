import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class WeatherAPI {
    private static HashMap<String,String> calls;
    private String APIKEY;
    private String IP;
    private JsonNode Node;

    public JsonNode getNode() {
        return Node;
    }

    public WeatherAPI() {
        calls = new HashMap<>();
        calls.put("IPLocation","/locations/v1/cities/ipaddress");
        calls.put("currentConditions","/currentconditions/v1/");
        try {
            setAPIKey();
            setIP();
            this.Node = setLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public HashMap<String,String> getCalls() {
        return calls;
    }

    private void setAPIKey() throws Exception {
        APIKEY = System.getenv("ACCUWEATHER_KEY");
        if ( APIKEY == null ) {
            throw new Exception("Missing Accuweather API key");
        }
    }

    public String getIP() {
        return IP;
    }

    public JsonNode setLocation() throws IOException {

        // API Call:
       // "http://dataservice.accuweather.com/locations/v1/cities/ipaddress?apikey=[key]&q=188.25.249.171"

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);


        StringBuffer endpoint = new StringBuffer("http://dataservice.accuweather.com"); // compose URL endpoint
        endpoint.append(calls.get("IPLocation"));
        endpoint.append("?apikey=");
        endpoint.append(APIKEY);
        endpoint.append("&q=");
        endpoint.append(this.IP);

        JsonNode node = objectMapper.readTree(new URL(endpoint.toString()));

        return node;

    }

    private void setIP() throws IOException,InterruptedException, URISyntaxException {
        String URL = "http://icanhazip.com";
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
        HttpRequest req = HttpRequest.newBuilder().uri(new URI(URL)).GET().build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        this.IP = resp.body().trim();

    }


}
