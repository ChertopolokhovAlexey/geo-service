package ru.netology.geo;

import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceMock implements GeoService{

    Location location;
    @Override
    public Location byIp(String ip) {
        return location;
    }

    public void setLocation(String ip) {
        if (ip.startsWith("172.")){
            this.location = new Location(null, Country.RUSSIA, null, 0);
        }
        if (ip.startsWith("96.")) {
            this.location = new Location(null, Country.USA, null, 0);
        }
        if (ip.startsWith("127.")) {
            this.location = new Location(null, null, null, 0);
        }
    }

    @Override
    public Location byCoordinates(double latitude, double longitude) {
        return null;
    }
}
