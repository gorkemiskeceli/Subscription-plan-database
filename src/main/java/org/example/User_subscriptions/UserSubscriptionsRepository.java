package org.example.User_subscriptions;

import org.example.Config.DataBaseConnectorConfig;
import org.example.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    // deneme deneme deneme

    public void createUserSubscriptions(String uuid, long user_id, long plans_id, Timestamp start_date, Timestamp end_date, Timestamp renewal_date, String status, Timestamp updated_at, String updated_by){
        String query = "INSERT INTO user_subscriptions(uuid, user_id, plans_id, start_date, end_date, renewal_date, status, updated_at, updated_by) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
             statement.setString(1, uuid);
             statement.setLong(2, user_id);
             statement.setLong(3, plans_id);
             statement.setTimestamp(4, start_date);
             statement.setTimestamp(5, end_date);
             statement.setTimestamp(6, renewal_date);
             statement.setString(7, status);
             statement.setTimestamp(8, updated_at);
             statement.setString(9, updated_by);
             statement.execute();

            System.out.println("User subscription has been created...");

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static List<User> listNonActiveUsers() {
        // Fixed SQL query string with proper spacing
        String query = "SELECT u.id, u.name, u.uuid, u.email, u.updated_at " +
                "FROM \"user\" u " +
                "JOIN user_subscriptions us ON u.id = us.user_id " +
                "WHERE us.status != 'active';";
        List<User> nonActiveUsers = new ArrayList<>();

        try (Connection connection = DataBaseConnectorConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String uuid = resultSet.getString("uuid");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setUuid(uuid);
                user.setUpdated_at(updatedAt);
                nonActiveUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nonActiveUsers;
    }

}
