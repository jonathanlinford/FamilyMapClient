package jonathanlinford.familymapclient.Model;


public class Location {
    private double latitude;
    private double longitude;
    private String country;
    private String city;

    public Location(double latitude, double longitude, String country, String city) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {

        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
