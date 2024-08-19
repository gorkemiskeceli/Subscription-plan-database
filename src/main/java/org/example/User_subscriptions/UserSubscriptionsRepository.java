package org.example.User_subscriptions;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class UserSubscriptionsRepository {
    public static void createUserSubscriptions(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query = "DROP TABLE IF EXISTS user_subscriptions;"+
                    "DROP SEQUENCE IF EXISTS user_subscriptions_id_seq;"+
                    "CREATE SEQUENCE user_subscriptions_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "CREATE TABLE user_subscriptions("+
                    "id INTEGER DEFAULT nextval('user_subscriptions_id_seq') NOT NULL,"+
                    "uuid VARCHAR(255) NOT NULL,"+
                    "user_id INTEGER NOT NULL,"+
                    "plans_id INTEGER NOT NULL,"+
                    "start_date TIMESTAMP NOT NULL,"+
                    "end_date TIMESTAMP NOT NULL,"+
                    "renewal_date TIMESTAMP NOT NULL,"+
                    "status VARCHAR(255) NOT NULL,"+
                    "updated_at TIMESTAMP NOT NULL,"+
                    "updated_by VARCHAR(255) NOT NULL,"+
                    "CONSTRAINT user_subscription_id PRIMARY KEY (id),"+
                    "CONSTRAINT user_subscription_plan_id_fkey FOREIGN KEY (plans_id) REFERENCES plans(id) NOT DEFERRABLE,"+
                    "CONSTRAINT user_subscription_user_id_fkey FOREIGN KEY (user_id) REFERENCES \"user\"(id) NOT DEFERRABLE"+
                    ");";
            statement.execute(query);
            System.out.println("User Subscriptions table has been created at database...");

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public UserSubscriptions createUserSubscriptions(UserSubscriptions userSubscriptions){
        String query = "INSERT INTO user_subscriptions(uuid, start_date, end_date, renewal_date, status, updated_at, updated_by) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1, userSubscriptions.getUuid());
            statement.setTimestamp(2, userSubscriptions.getStart_date());
            statement.setTimestamp(3, userSubscriptions.getEnd_date());
            statement.setTimestamp(4, userSubscriptions.getRenewal_date());
            statement.setString(5, userSubscriptions.getStatus());
            statement.setTimestamp(6, userSubscriptions.getUpdate_at());
            statement.setString(7, userSubscriptions.getUpdated_by());
            statement.executeUpdate();
            System.out.println("User subscriptions have been updated...");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return userSubscriptions;
    }
}
