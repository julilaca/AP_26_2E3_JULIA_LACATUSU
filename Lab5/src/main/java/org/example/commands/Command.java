package org.example.commands;
import org.example.exceptions.ResourceException;

public interface Command {
    void execute() throws ResourceException;
}