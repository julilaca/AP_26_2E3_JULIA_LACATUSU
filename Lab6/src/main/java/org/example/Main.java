    package org.example;

    import freemarker.template.*;
    import java.io.*;
    import java.sql.*;
    import java.util.*;
    import java.awt.Desktop;

    public class Main {
        public static void main(String[] args) throws Exception {
            generateReport();
        }

        public static void generateReport() throws Exception {

            List<Map<String, Object>> movies = new ArrayList<>();
            try (Connection con = Database.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM movie_report_view")) {

                while (rs.next()) {
                    Map<String, Object> m = new HashMap<>();
                    m.put("title", rs.getString("title"));
                    m.put("genre", rs.getString("genre_name"));
                    m.put("score", rs.getDouble("score"));
                        movies.add(m);
                }
            }

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_33);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));

            Map<String, Object> input = new HashMap<>();
            input.put("movies", movies);

            try (Writer out = new FileWriter(new File("report.html"))) {
                cfg.getTemplate("report.ftl").process(input, out);
            }

            File file = new File("report.html");
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        }
    }

/* COMPULSORY import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            GenreDAO genres = new GenreDAO();

            genres.create("SciF");
            genres.create("nonfictio");

            System.out.println("ID for Sci-Fi: " + genres.findByName("Sci-Fi"));
            System.out.println("ID 1: " + genres.findById(1));

            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
} */
