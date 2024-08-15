package org.example.User_feature_limits;

import org.example.Config.DataBaseConnectorConfig;

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
}
