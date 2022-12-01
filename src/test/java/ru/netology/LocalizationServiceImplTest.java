package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;


class LocalizationServiceImplTest {

    LocalizationServiceImpl localizationService;
    Country country;

    @Test
    void locale() {
        country = Country.RUSSIA;
        localizationService = new LocalizationServiceImpl();
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(country));
        Assertions.assertNotEquals("Welcome", localizationService.locale(country));
    }
}