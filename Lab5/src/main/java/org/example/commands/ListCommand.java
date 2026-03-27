package org.example.commands;

import org.example.exceptions.ResourceException;
import org.example.repository.Catalog;

public class ListCommand implements Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws ResourceException {

    }


}