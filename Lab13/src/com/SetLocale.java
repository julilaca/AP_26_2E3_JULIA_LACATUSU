package com;
import java.util.Locale;

public class SetLocale implements Command {
    private String languageTag;
    public SetLocale(String languageTag) { this.languageTag = languageTag; }

    public void execute(Locale currentLocale) {
        System.out.println("Locale set to: " + languageTag);
    }
}