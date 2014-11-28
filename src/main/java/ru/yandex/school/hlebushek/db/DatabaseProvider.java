package ru.yandex.school.hlebushek.db;

import java.io.IOException;
import static java.lang.String.format;
import static java.nio.file.Files.createTempDirectory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import org.flywaydb.core.Flyway;
import org.javalite.activejdbc.Base;

@Provider
public class DatabaseProvider implements ContainerRequestFilter {
    private static final String DB_USER = "sa";
    private static String dbUrl;
    
    static {
        try {
            dbUrl = format("jdbc:h2:mem:%s/%s,user=%s", getDbPath(), getDbName(), DB_USER);
            System.out.println(format("Starting embedded database with url '%s' ...", dbUrl));
            openConnection();
            Flyway flyway = new Flyway();
            flyway.setDataSource(dbUrl, DB_USER, null);
            flyway.migrate();
        } catch (Exception e) {
            System.out.println("Failed to start embedded database: ".concat(e.getMessage()));
            System.exit(1);
        }
    }

    private static void openConnection() {
        if (!Base.hasConnection()) {
            Base.open(org.h2.Driver.class.getName(), dbUrl, DB_USER, "");
        }
    }

    private static String getDbName() {
        return getProperty("db.name", "default");
    }

    private static String getDbPath() throws IOException {
        return getProperty("db.path", createTempDirectory("blog").toAbsolutePath().toString());
    }

    private static String getProperty(String key, String defaultValue) {
        final String value = System.getProperty(key);
        return (value == null) ? defaultValue : value;
    }
    
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        openConnection();
    }
    
    
}
