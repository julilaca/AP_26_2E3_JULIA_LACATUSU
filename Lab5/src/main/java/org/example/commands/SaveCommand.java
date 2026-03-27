package org.example.commands;
import org.example.model.Resource;
import org.example.exceptions.ResourceException;
import org.example.repository.Catalog;

import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand implements Command {
    private Catalog catalog;
    private String filePath;

    public SaveCommand(Catalog catalog, String filePath) {
        this.catalog = catalog;
        this.filePath = filePath;
    }

    @Override
    public void execute() throws ResourceException {
        try (FileWriter f = new FileWriter(filePath)) {
            for (Resource r : catalog.getItems())
                f.write(r.toString() + "\n");
            System.out.println("saved to " + filePath);
        } catch (IOException e) {
            throw new ResourceException("failed to save catalog: " + e.getMessage());
        }
    }
}