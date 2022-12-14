package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;


class MessageSenderImplTest {

    final String IP2 = GeoServiceImpl.MOSCOW_IP;
    final String IP3 = GeoServiceImpl.NEW_YORK_IP;
    final String RUS = "Алфавит";
    final String ENG = "Alphabet";
    final String HEAD = "x-real-ip";
    MessageSenderImpl messageSender;
    LocalizationService localizationService;
    GeoService geoService;
    Map<String,String> headers = new HashMap<>();
    @BeforeEach
    void send() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService,  localizationService);
    }
    @Test
    void  sendRusTest(){

        Mockito.when(geoService.byIp(IP2))
                .thenReturn(new Location(null,Country.RUSSIA,null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(RUS);
        headers.put(HEAD,IP2);
        Assertions.assertEquals(RUS ,messageSender.send(headers));
        Mockito.verify(geoService, Mockito.times(1)).byIp(IP2);
        Mockito.verify(localizationService, Mockito.atLeastOnce()).locale(Country.RUSSIA);
    }

    @Test
    void  sendEngTest() {
        Mockito.when(geoService.byIp(IP3))
                .thenReturn(new Location(null,Country.USA,null, 0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(ENG);
        headers.put(HEAD,IP3);
        Assertions.assertEquals(ENG ,messageSender.send(headers));
    }

    @Test
    void  sendPartialMatchRusTest() {
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location(null,Country.RUSSIA,null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(RUS);
        headers.put(HEAD,"172.0.");
        Assertions.assertEquals(RUS ,messageSender.send(headers));
    }

    @Test
    void  sendPartialMatchEngTest() {
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location(null,Country.USA,null, 0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(ENG);
        headers.put(HEAD,"96.0.");
        Assertions.assertEquals(ENG ,messageSender.send(headers));
    }


}