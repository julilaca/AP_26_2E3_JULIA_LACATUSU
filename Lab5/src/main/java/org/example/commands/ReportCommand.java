package org.example.commands;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.example.exceptions.ResourceException;
import org.example.repository.Catalog;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private Catalog catalog;
    private String outputPath;

    public ReportCommand(Catalog catalog, String outputPath) {
        this.catalog = catalog;
        this.outputPath = outputPath;
    }

    @Override
    public void execute() throws ResourceException {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_33);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            cfg.setDefaultEncoding("UTF-8");

            Template template = cfg.getTemplate("report.txt");
            Map<String, Object> data = new HashMap<>();
            data.put("resources", catalog.getItems());

            File output = new File(outputPath);
            try (FileWriter writer = new FileWriter(output)) {
                template.process(data, writer);
            }

            Desktop.getDesktop().open(output);
            System.out.println(outputPath);

        } catch (IOException | TemplateException e) {
            throw new ResourceException("failed to open: " + e.getMessage());
        }
    }
}
