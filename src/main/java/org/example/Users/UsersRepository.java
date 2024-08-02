package org.example.Users;

import org.example.Config.DataBaseConnectorConfig;

import javax.swing.table.TableRowSorter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersRepository {
    public static void createUserTable() {
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()) {

            String query = "DROP SEQUENCE IF EXISTS user_id_seq CASCADE;" +
                    "CREATE SEQUENCE user_id_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START 1;" +
                    "CREATE TABLE IF NOT EXISTS users(" +
                    "user_id INTEGER DEFAULT nextval('user_id_seq') PRIMARY KEY," +
                    "full_name VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL);";


            statement.execute(query);
            System.out.println("Plans Table has been created!!!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Users saveUser(Users users){
        String query = "INSERT INTO users(full_name, email, password) VALUES (?,?,?)";
        try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
            statement.setString(1, users.getFull_name());
            statement.setString(2, users.getEmail());
            statement.setString(3, users.getPassword());

            statement.execute();
            System.out.println("User has been created: "+users.getFull_name());
        }catch (SQLException e){
            throw new RuntimeException("User can not be created: "+users.getFull_name(), e);
        }
        return users;
    }

}
