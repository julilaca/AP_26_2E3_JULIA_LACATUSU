package org.example;

import org.example.model.Resource;
import org.example.repository.Catalog;
import org.example.exceptions.ResourceException;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Resource doc = new Resource("res1", "testing", "C:/Users/julia/Downloads/testphrase.txt", "2000", "Testing");

        catalog.add(doc);
        System.out.println(catalog);

        try {
            open(doc);
        } catch (ResourceException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void open(Resource res) throws ResourceException {
        File file = new File(res.getLocation());

        if (!file.exists()) {
            throw new ResourceException("file not found");
        }

        if (!Desktop.isDesktopSupported()) {
            throw new ResourceException("desktop unsupported");
        }

        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            throw new ResourceException("failed to open file");
        }
    }
}
