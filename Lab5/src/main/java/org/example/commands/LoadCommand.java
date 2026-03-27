package org.example.commands;

import org.example.exceptions.ResourceException;
import org.example.repository.Catalog;

public class LoadCommand implements Command {
    private Catalog catalog;
    private String filePath;

    public LoadCommand(Catalog catalog, String filePath) {
        this.catalog = catalog;
        this.filePath = filePath;
    }


    @Override
    public void execute() throws ResourceException {
        System.out.println(filePath);
    }
}
