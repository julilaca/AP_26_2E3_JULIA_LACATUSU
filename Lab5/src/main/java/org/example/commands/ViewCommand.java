package org.example.commands;

import org.example.exceptions.ResourceException;
import org.example.model.Resource;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command {
    private Resource resource;

    public ViewCommand(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void execute() throws ResourceException {
        File file = new File(resource.getLocation());

        if (!file.exists())
            throw new ResourceException("file not found");

        if (!Desktop.isDesktopSupported())
            throw new ResourceException("desktop unsupported");

        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            throw new ResourceException("failed to open file");
        }
    }
}
