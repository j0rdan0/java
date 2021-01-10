import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); // needs to be set when not using all fields of response
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY); // needs to be set or it will fuck the entire response
        String URL = "https://dataservice.accuweather.com/locations/v1/regions?apikey=XEieSk9NynHwWu3YC0W3qVAckyckKGFA";

        ArrayList<LocationTest> x;
        try {
          x= objectMapper.readValue(new URL(URL), new TypeReference<ArrayList<LocationTest>>(){});
            for ( LocationTest lt : x) {
                System.out.println(lt.getID());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }





    }
}
