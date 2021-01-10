import java.io.IOException;
import java.net.*;
import java.net.http.*;

//http://dataservice.accuweather.com/locations/v1/cities/ipaddress?apikey=XEieSk9NynHwWu3YC0W3qVAckyckKGFA&q=188.25.249.171

public class Location {
    private String LocalizedName;
    private String Key;
    private String IP;
    private Country country;

    public Location() {
        this.LocalizedName = "";
        this.Key ="";
        this.IP = "";
        this.country = null;

        try {
            setIP();
            setKey();

            }
        catch (IOException e) {

        }
        catch (InterruptedException e) {

        }
        catch (URISyntaxException e){

        }

    }

    private void setIP() throws IOException,InterruptedException, URISyntaxException {
        String URL = "http://icanhazip.com";
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
        HttpRequest req = HttpRequest.newBuilder().uri(new URI(URL)).GET().build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        this.IP = resp.body().trim();

    }
    public String getIP() {
        return this.IP;
    }
    private void setKey()  {
       //
    }
    public String getkey()  {
        return this.Key;
    }
}

class Country {
    private String LocalizedName;
}