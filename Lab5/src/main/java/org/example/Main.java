package org.example;

import org.example.commands.*;
import org.example.exceptions.ResourceException;
import org.example.model.Resource;
import org.example.repository.Catalog;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        catalog.add(new Resource("123", "julia", "d:/books/programming/", "2026", "testing"));
        catalog.add(new Resource("456", "julia", "https://testing.com/html/index.html", "2025", "testing2"));

        try {
            new ListCommand(catalog).execute();
            new SaveCommand(catalog, "catalog.txt").execute();
            new ReportCommand(catalog, "report.html").execute();
        } catch (ResourceException e) {
            System.err.println(e.getMessage());
        }
    }
}
