package org.example.Limits;

import org.example.Config.DataBaseConnectorConfig;
import org.example.User_subscriptions.UserSubscriptions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LimitsRepository {
    public static void createLimits(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query = "DROP TABLE IF EXISTS limits;"+
                    "DROP SEQUENCE IF EXISTS limits_id_seq;"+
                    "CREATE SEQUENCE limits_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "CREATE TABLE limits("+
                    "id INTEGER DEFAULT nextval('limits_id_seq') NOT NULL,"+
                    "plan_id INTEGER NOT NULL,"+
                    "feature_id INTEGER NOT NULL,"+
                    "updated_at TIMESTAMP NOT NULL,"+
                    "updated_by VARCHAR(255) NOT NULL,"+
                    "CONSTRAINT limits_feature_id_fkey FOREIGN KEY (feature_id) REFERENCES features(id) NOT DEFERRABLE,"+
                    "CONSTRAINT limits_plant_id_fkey FOREIGN KEY (plan_id) REFERENCES plans(id) NOT DEFERRABLE"+
                    ");";
            statement.execute(query);
            System.out.println("Limits table has been created in the database...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
  public Limits createLimits(Limits limits){
        String query = "INSER INTO limits(updated_at, updated_by) VALUES (?,?)";
        try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
            statement.setTimestamp(1, limits.getUpdated_at());
            statement.setString(2, limits.getUpdated_by());
           statement.executeUpdate();
            System.out.println("Limits have been updated!!!");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
   return limits;
  }
}
