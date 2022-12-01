package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.geo.GeoServiceMock;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceMock;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;


class MessageSenderImplTest {

    final String IP2 = GeoServiceImpl.MOSCOW_IP;
    final String IP3 = GeoServiceImpl.NEW_YORK_IP;
    final String RUS = "Алфавит";
    final String ENG = "Alphabet";
    MessageSenderImpl messageSender;
    LocalizationService localizationService;
    GeoService geoService;
    Map<String,String> headers = new HashMap<>();
    @BeforeEach
    void send() {
        geoService = new GeoServiceMock();
        localizationService = new LocalizationServiceMock();
        messageSender = new MessageSenderImpl(geoService,  localizationService);
    }
    @Test
    void  sendRusTest(){
       ((LocalizationServiceMock)localizationService).setLocale(Country.RUSSIA);
       ((GeoServiceMock)geoService).setLocation(IP2);
        Assertions.assertEquals(RUS ,messageSender.send(headers));
    }

    @Test
    void  sendUsaTest() {
        ((LocalizationServiceMock)localizationService).setLocale(Country.USA);
        ((GeoServiceMock)geoService).setLocation(IP3);
        Assertions.assertEquals(ENG ,messageSender.send(headers));
    }

}