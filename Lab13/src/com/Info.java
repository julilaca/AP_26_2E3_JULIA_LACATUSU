package com;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;

public class Info implements Command {
    public void execute(Locale locale) {

        System.out.println("Country: " + locale.getDisplayCountry(locale));
        System.out.println("Language: " + locale.getDisplayLanguage(locale));

        System.out.println("Currency: " + java.util.Currency.getInstance(locale).getDisplayName(locale));

        java.text.DateFormatSymbols dfs = java.text.DateFormatSymbols.getInstance(locale);
        String[] weekdays = dfs.getWeekdays();

        System.out.println("Week Days: " + String.join(", ", java.util.Arrays.copyOfRange(weekdays, 1, weekdays.length)));

        String[] months = dfs.getMonths();
        System.out.println("Months: " + String.join(", ", months));


        java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.FULL).withLocale(locale);
        System.out.println("Today: " + java.time.LocalDate.now().format(dtf));
    }
}
