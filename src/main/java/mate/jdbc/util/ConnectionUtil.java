package mate.jdbc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static final Logger logger = LogManager.getLogger(ConnectionUtil.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can not load JDBC driver for mySQL", e);
        }
    }

    public static Connection getConnection() {
        logger.info("getting connection to DB...");
        try {
            Properties dbProperties = new Properties();
            dbProperties.put("user", "root");
            dbProperties.put("password", "root12341234");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/taxi_service_schema",
                    dbProperties);
            logger.info("successful connection to DB -> {}", connection.getCatalog());
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to manufacturers DB", e);
        }
    }
}