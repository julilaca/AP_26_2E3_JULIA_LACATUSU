package com;
import java.util.Locale;

public class DisplayLocales implements Command {
    public void execute(Locale currentLocale) {
        Locale[] available = Locale.getAvailableLocales();
        for (Locale loc : available) {
            System.out.println(loc.getDisplayCountry() + "\t" + loc.getDisplayLanguage(loc)); //
        }
    }
}