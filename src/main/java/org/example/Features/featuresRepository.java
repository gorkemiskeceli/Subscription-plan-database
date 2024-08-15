package org.example.Features;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.SQLException;
import java.sql.Statement;

public class featuresRepository {
    public static void createFeatures(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query = "DROP TABLE IF EXISTS features;" +
                    "DROP SEQUENCE IF EXISTS features_id_seq;" +
                    "CREATE SEQUENCE features_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;" +
                    "CREATE TABLE features(" +
                    "id INTEGER DEFAULT nextval('features_id_seq') NOT NULL," +
                    "name VARCHAR(255) NOT NULL," +
                    "description VARCHAR(255) NOT NULL," +
                    "updated_at TIMESTAMP NOT NULL," +
                    "updated_by VARCHAR(255) NOT NULL," +
                    "uuid VARCHAR(255) NOT NULL UNIQUE," +
                    "CONSTRAINT pk_features PRIMARY KEY (id)" +
                    ");";

            statement.execute(query);
            System.out.println("Features table has been created in the database...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
