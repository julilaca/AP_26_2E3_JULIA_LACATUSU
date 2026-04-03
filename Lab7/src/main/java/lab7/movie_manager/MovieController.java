package lab7.movie_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String showMovies() {

        String sql = "SELECT * FROM movie_report_view";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);


        StringBuilder html = new StringBuilder("<html><body><h1>Movie Manager</h1><table border='1'>");
        html.append("<tr><th>Title</th><th>Genre</th><th>Score</th></tr>");

        for (Map<String, Object> row : rows) {
            html.append("<tr>")
                    .append("<td>").append(row.get("title")).append("</td>")
                    .append("<td>").append(row.get("genre_name")).append("</td>")
                    .append("<td>").append(row.get("score")).append("</td>")
                    .append("</tr>");
        }

        html.append("</table></body></html>");
        return html.toString();
    }
}