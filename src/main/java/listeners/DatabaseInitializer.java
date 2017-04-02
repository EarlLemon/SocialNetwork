package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Listener for initialize database
 */
@WebListener
public class DatabaseInitializer implements ServletContextListener {
    private static final Logger Log = LogManager.getLogger(DatabaseInitializer.class);


    @Resource(name = "jdbc/h2")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Log.info("Database initialization started...");
        Pattern pattern = Pattern.compile("^\\d+\\.sql$");
        Path sqlDirectoryPath = Paths.get(sce.getServletContext().getRealPath("/WEB-INF/classes/sql"));
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             DirectoryStream<Path> paths = Files.newDirectoryStream(sqlDirectoryPath)) {
            for (Path filePath : paths) {
                if (pattern.matcher(filePath.toFile().getName()).find()) {
                    statement.addBatch(
                            Files.lines(filePath).collect(Collectors.joining())
                    );

                }

            }
            statement.executeBatch();


        } catch (SQLException e) {
            Log.error("Execution queries exception: " + e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
