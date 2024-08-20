package org.example.User_feature_limits;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class UserFeatureLimitsRepository {
    public static void createUserFeatureLimits(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query =  "DROP TABLE IF EXISTS user_feature_limits;"+
                    "DROP SEQUENCE IF EXISTS user_feature_limits_id_seq;"+
                    "CREATE SEQUENCE user_feature_limits_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
            "CREATE TABLE user_feature_limits("+
            "id INTEGER  DEFAULT nextval('user_feature_limits_id_seq') NOT NULL,"+
            "uuid VARCHAR(255) NOT NULL,"+
            "features_id INTEGER NOT NULL,"+
            "user_limits INTEGER NOT NULL,"+
            "updated_at TIMESTAMP NOT NULL,"+
            "updated_by VARCHAR(255) NOT NULL,"+
            "user_id INTEGER NOT NULL,"+
            "CONSTRAINT user_feature_limits_id PRIMARY KEY (id),"+
            "CONSTRAINT user_feature_limits_feature_id_fkey FOREIGN KEY (features_id) REFERENCES features(id) NOT DEFERRABLE,"+
            "CONSTRAINT user_feature_limits_user_id_fkey FOREIGN KEY (user_id) REFERENCES \"user\"(id) NOT DEFERRABLE"+
            ");";

            statement.execute(query);
            System.out.println("User Feature Limits table has been created at database...");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void createUserFeatureLimit(String uuid, long features_id, int user_limits, Timestamp updated_at, String updated_by, long user_id){
       String query = "INSERT INTO user_feature_limits(uuid, features_id, user_limits, updated_at, updated_by, user_id) VALUES (?,?,?,?,?,?)";
       try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1, uuid);
            statement.setLong(2, features_id);
            statement.setInt(3, user_limits);
            statement.setTimestamp(4, updated_at);
            statement.setString(5, updated_by);
            statement.setLong(6, user_id);

            statement.execute();
           System.out.println("Limits have been updated...");
       }catch (SQLException e){
           throw new RuntimeException(e);
       }
    }

}
