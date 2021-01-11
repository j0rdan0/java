import com.fasterxml.jackson.databind.JsonNode;


public class Location {
    private String LocalizedName;

    public String getLocalizedName() {
        return LocalizedName;
    }

    public void setLocalizedName(String localizedName) {
        LocalizedName = localizedName;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private String Key;
    private String IP;
    private Country country;

    public Location() {
        this.LocalizedName = "";
        this.IP = "";
        this.Key = "";
        this.country = null;
    }


    public Location createLocation() {
        Location l = new Location();
        Country c = new Country();

        WeatherAPI weatherAPI = new WeatherAPI();
        JsonNode node = weatherAPI.getNode();
        l.setLocalizedName(node.get("LocalizedName").asText());
        l.setIP(weatherAPI.getIP());
        l.setKey(node.get("Key").asText());
        c.setLocalizedName(node.get("Country").get("LocalizedName").asText());
        l.country = c;
        return l;
    }

}

class Country {
    private String LocalizedName;

    public String getLocalizedName() {
        return LocalizedName;
    }

    public void setLocalizedName(String localizedName) {
        LocalizedName = localizedName;
    }
}
