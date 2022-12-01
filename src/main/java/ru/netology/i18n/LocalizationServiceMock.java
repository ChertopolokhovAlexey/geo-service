package ru.netology.i18n;

import ru.netology.entity.Country;

public class LocalizationServiceMock implements LocalizationService {

    String locale;

    @Override
    public String locale(Country country) {
        return locale;
    }

    public void setLocale(Country country) {
        if(country.equals(Country.RUSSIA)){
            locale = "Алфавит";
        } else {
            locale = "Alphabet";
        }

    }

}
