import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class APICalls {
    private static HashMap<String,String> calls;
    private String APIKEY;

    public APICalls() {
        calls = new HashMap<>();
        calls.put("IPLocation","/locations/v1/cities/ipaddress");
        calls.put("currentConditions","/currentconditions/v1/");
        try {
            setAPIKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            setLocation();
        } catch (IOException e) {
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

    private void setLocation() throws IOException {

        // API Call:
       // "http://dataservice.accuweather.com/locations/v1/cities/ipaddress?apikey=[key]&q=188.25.249.171"

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        Location location = new Location();

        StringBuffer endpoint = new StringBuffer("http://dataservice.accuweather.com"); // compose URL endpoint
        endpoint.append(calls.get("IPLocation"));
        endpoint.append("?apikey=");
        endpoint.append(APIKEY);
        endpoint.append("&q=");
        endpoint.append(location.getIP());

        location = objectMapper.readValue(new URL(endpoint.toString()),Location.class);

    }


}
