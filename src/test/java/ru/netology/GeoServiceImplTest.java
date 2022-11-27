package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

class GeoServiceImplTest {
    final String IP1 = "127.0.0.1";
    final String IP2 = "172.0.32.11";
    final String IP3 = "96.44.183.149";

    GeoServiceImpl geoServiceImpl;

    @BeforeEach
    void getGeoServiceImpl() {
        geoServiceImpl = new GeoServiceImpl();
    }

    @Test
    void byIpLOCAL() {
        Location locationLocalhost = new Location(null, null, null, 0);
        Location location1 = geoServiceImpl.byIp(IP1);

        Assertions.assertEquals(location1.getCity(), locationLocalhost.getCity());
        Assertions.assertEquals(location1.getCountry(), locationLocalhost.getCountry());
        Assertions.assertEquals(location1.getStreet(), locationLocalhost.getStreet());
        Assertions.assertEquals(location1.getBuiling(), locationLocalhost.getBuiling());
    }

    @Test
    void byIpRUS() {
        Location locationRUS = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location location1 = geoServiceImpl.byIp(IP2);

        Assertions.assertSame(location1.getCity(), locationRUS.getCity());
        Assertions.assertEquals(location1.getCountry(), locationRUS.getCountry());
        Assertions.assertEquals(location1.getStreet(), locationRUS.getStreet());
        Assertions.assertEquals(location1.getBuiling(), locationRUS.getBuiling());
    }

    @Test
    void byIpUSA() {
        Location locationUSA = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location location1 = geoServiceImpl.byIp(IP3);

        Assertions.assertSame(location1.getCity(), locationUSA.getCity());
        Assertions.assertEquals(location1.getCountry(), locationUSA.getCountry());
        Assertions.assertEquals(location1.getStreet(), locationUSA.getStreet());
        Assertions.assertEquals(location1.getBuiling(), locationUSA.getBuiling());
    }

    @Test
    void byIpPartiallyIP() {

        Location locationRUS = new Location("Moscow", Country.RUSSIA, null, 0);
        Location location1 = geoServiceImpl.byIp("172.");

        Assertions.assertSame(location1.getCity(), locationRUS.getCity());
        Assertions.assertEquals(location1.getCountry(), locationRUS.getCountry());
        Assertions.assertEquals(location1.getStreet(), locationRUS.getStreet());
        Assertions.assertEquals(location1.getBuiling(), locationRUS.getBuiling());

        Location locationUSA = new Location("New York", Country.USA, null, 0);
        Location location2 = geoServiceImpl.byIp("96.");

        Assertions.assertSame(location2.getCity(), locationUSA.getCity());
        Assertions.assertEquals(location2.getCountry(), locationUSA.getCountry());
        Assertions.assertEquals(location2.getStreet(), locationUSA.getStreet());
        Assertions.assertEquals(location2.getBuiling(), locationUSA.getBuiling());

    }

}
