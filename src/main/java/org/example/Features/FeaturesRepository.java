package org.example.Features;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FeaturesRepository {

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
        public Features createFeatures(Features features){
            String query = "INSERT INTO features(name, description, updated_at, updated_by, uuid) VALUES (?,?,?,?,?)";
            try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
                statement.setString(1, features.getName());
                statement.setString(2, features.getDescription());
                statement.setTimestamp(3, features.getUpdated_at());
                statement.setString(4, features.getDescription());
                statement.setString(5, features.getUuid());
                statement.executeUpdate();
                System.out.println("Feature has been saved with name of:" +features.getName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return features;
        }
}


