package ru.yandex.school.hlebushek.service;

import java.io.IOException;
import static java.lang.String.format;
import static java.nio.file.Files.createTempDirectory;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Request;
import javax.ws.rs.ext.Provider;
import org.flywaydb.core.Flyway;
import org.javalite.activejdbc.Base;
import sun.security.x509.URIName;

@Provider
public class DatabaseProvider implements ContainerRequestFilter{
    private static final String DBUSER = "sa";
    private static String dbUrl;
    
    static {
        try {
            dbUrl = format("jdbc:h2:file:%s/%s,user=%s", getDbPath(), getDbName(), DBUSER);
            System.out.println(format("Starting embedded database with url '%s' ...", dbUrl));
            openConnection();
            Flyway flyway = new Flyway();
            flyway.setDataSource(dbUrl, DBUSER, null);
            flyway.migrate();
        } catch (Exception e) {
            System.out.println("Failed to start embedded database: ".concat(e.getMessage()));
            System.exit(1);
        }
    }

    public static void openConnection() {
        if (!Base.hasConnection()) {
            Base.open(org.h2.Driver.class.getName(), dbUrl, DBUSER, "");
        }
    }

    private static String getDbName() {
        String dbName = getProperty("db.name", "default");
        System.out.println("Use DB Name: ".concat(dbName));
        return dbName;
    }

    private static String getDbPath() throws IOException {
        String dbPath = getProperty("db.path", createTempDirectory("blog").toAbsolutePath().toString());
        System.out.println("Use DB Path: ".concat(dbPath));
        return dbPath;
    }

    private static String getProperty(String key, String defaultValue) {
        final String value = System.getProperty(key);
        return (value == null) ? defaultValue : value;
    }
    //@Context uriinfo
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        Integer userId = (Integer) crc.getProperty("user_id");
        if (userId == null && !crc.getUriInfo().getRequestUri().equals(crc.getUriInfo().getBaseUri()))
            return;
        openConnection();
    }
    
    
}
