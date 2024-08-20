package org.example.Limits;

import org.example.Config.DataBaseConnectorConfig;
import org.example.User_subscriptions.UserSubscriptions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class LimitsRepository {
    public static void createLimits(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query = "DROP TABLE IF EXISTS limits;" +
                    "DROP SEQUENCE IF EXISTS limits_id_seq;" +
                    "CREATE SEQUENCE limits_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;" +
                    "CREATE TABLE limits (" +
                    "id INTEGER DEFAULT nextval('limits_id_seq') NOT NULL, " +
                    "plans_id INTEGER NOT NULL, " +
                    "features_id INTEGER NOT NULL, " +
                    "limit_value INTEGER NOT NULL, " +
                    "updated_at TIMESTAMP NOT NULL, " +
                    "updated_by VARCHAR(255) NOT NULL, " +
                    "CONSTRAINT limits_id PRIMARY KEY (id), " +
                    "CONSTRAINT limits_feature_id_fkey FOREIGN KEY (features_id) REFERENCES features(id) NOT DEFERRABLE, " +
                    "CONSTRAINT limits_plans_id_fkey FOREIGN KEY (plans_id) REFERENCES plans(id) NOT DEFERRABLE" +
                    ");";
            statement.execute(query);
            System.out.println("Limits table has been created in the database...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
  public void createLimit(long plans_id, long features_id, int limit_value,Timestamp updated_at, String updated_by){
        String query = "INSERT INTO limits(plans_id, features_id, limit_value,updated_at, updated_by) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
            statement.setLong(1, plans_id);
            statement.setLong(2, features_id);
            statement.setInt(3, limit_value);
            statement.setTimestamp(4, updated_at);
            statement.setString(5, updated_by);
            statement.execute();
            System.out.println("Limit have been updated!!!");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

  }
}
