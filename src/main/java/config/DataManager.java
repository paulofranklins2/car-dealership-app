package config;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataManager {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/car_dealership";

    public void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 received: " + args.length);
        }
    }

    public BasicDataSource getDataSource(String[] args) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(DB_URL);
        basicDataSource.setUsername(args[0]);
        basicDataSource.setPassword(args[1]);
        return basicDataSource;
    }
}
