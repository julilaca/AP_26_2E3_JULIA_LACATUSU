package com;
import java.util.Locale;

public interface Command {
    void execute(Locale currentLocale);
}