package org.example.User_feature_limits;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserFeatureLimitsRepository {
    public static void createUserFeatureLimits(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query =  "DROP TABLE IF EXISTS user_feature_limits;"+
            "CREATE TABLE user_feature_limits("+
            "id INTEGER NOT NULL,"+
            "uuid VARCHAR(255) NOT NULL,"+
            "feature_id INTEGER NOT NULL,"+
            "limits INTEGER NOT NULL,"+
            "updated_at TIMESTAMP NOT NULL,"+
            "updated_by VARCHAR(255) NOT NULL,"+
            "user_id INTEGER NOT NULL,"+
            "CONSTRAINT user_feature_limits_id PRIMARY KEY (id),"+
            "CONSTRAINT user_feature_limits_feature_id_fkey FOREIGN KEY (feature_id) REFERENCES features(id) NOT DEFERRABLE,"+
            "CONSTRAINT user_feature_limits_user_id_fkey FOREIGN KEY (user_id) REFERENCES \"user\"(id) NOT DEFERRABLE"+
            ");";

            statement.execute(query);
            System.out.println("User Feature Limits table has been created at database...");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public UserFeatureLimits createUserFeatureLimits(UserFeatureLimits userFeatureLimits){
       String query = "INSERT INTO user_feature_limits(uuid, limits, updated_at, updated_by) VALUES (?,?,?,?)";
       try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
           statement.setString(1, userFeatureLimits.getUuid());
           statement.setInt(2, userFeatureLimits.getLimits());
           statement.setTimestamp(3, userFeatureLimits.getUpdated_at());
           statement.setString(4, userFeatureLimits.getUpdated_by());
           statement.executeUpdate();
           System.out.println("Limits have been updated...");
       }catch (SQLException e){
           throw new RuntimeException(e);
       }



        return userFeatureLimits;
    }

}
