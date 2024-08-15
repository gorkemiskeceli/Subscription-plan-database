package org.example.User;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    public static void createUser(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query = "DROP TABLE IF EXISTS \"user\";" +
                    "DROP SEQUENCE IF EXISTS user_id_seq;" +
                    "CREATE SEQUENCE user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;" +
                    "CREATE TABLE \"user\"(" +
                    "id INTEGER DEFAULT nextval('user_id_seq') NOT NULL," +
                    "name VARCHAR(255) NOT NULL," +
                    "uuid VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL," +
                    "updated_at TIMESTAMP NOT NULL," +
                    "CONSTRAINT pk_user PRIMARY KEY (id)" +
                    ");";
            statement.execute(query);
            System.out.println("User table has been created at database...");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
