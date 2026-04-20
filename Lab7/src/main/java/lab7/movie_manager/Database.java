package lab7.movie_manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static HikariDataSource dataSource;

    private Database() {}

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/movie_db");
            config.setUsername("postgres");
            config.setPassword("STUDENT");
            config.setMaximumPoolSize(10);

            dataSource = new HikariDataSource(config);
        }
        return dataSource.getConnection();
    }
}