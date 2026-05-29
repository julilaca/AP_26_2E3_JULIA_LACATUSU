package app;

import com.*;
import java.text.MessageFormat;
import java.util.*;

public class LocaleExplore {
    public static void main(String[] args) {
        Locale currentLocale = Locale.getDefault();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ResourceBundle messages = ResourceBundle.getBundle("res.Messages", currentLocale);
            System.out.print(messages.getString("prompt"));

            String input = scanner.nextLine();
            String[] tokens = input.split(" ");

            if (tokens[0].equals("quit")) break;

            try {
                switch (tokens[0]) {
                    case "locales": new DisplayLocales().execute(currentLocale); break;
                    case "set":
                        currentLocale = Locale.forLanguageTag(tokens[1]);
                        System.out.println(MessageFormat.format(messages.getString("locale.set"), currentLocale));
                        break;
                    case "info": new Info().execute(currentLocale); break;
                    default: System.out.println(messages.getString("invalid"));
                }
            } catch (Exception e) {
                System.out.println(messages.getString("invalid"));
            }
        }
    }
}
