package org.example.Users;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class UserPlanMapper {
    public static void createUserPlanTable() {
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()) {
            String query = "DROP SEQUENCE IF EXISTS u_p_mapper_id;" + //adding all info into one.
                            "CREATE SEQUENCE u_p_mapper_id INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1;" +
                            "CREATE TABLE IF NOT EXISTS user_plan_mapper (" +
                            "u_p_mapper_id INTEGER DEFAULT nextval('u_p_mapper_id') PRIMARY KEY NOT NULL," +
                            "user_id INTEGER NOT NULL," +
                            "plans_id INTEGER NOT NULL," +
                            "start_date DATE," +
                            "end_date DATE," +
                            "feature_limit INTEGER," +
                            "CONSTRAINT fk_plans_id FOREIGN KEY (plans_id) REFERENCES plans(plans_id)," +
                            "CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id));";

            statement.execute(query);
            System.out.println("UserPlan table has been created in the database..");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveToUserPlansMapper(long user_id, long plans_id, Timestamp star_date, Timestamp end_date, int feature_limit){
        String query = "INSERT INTO user_plan_mapper(user_id,plans_id,start_date,end_date,feature_limit) VALUES(?,?,?,?,?)";

        try(PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
            statement.setLong(1, user_id);
            statement.setLong(2, plans_id);
            statement.setTimestamp(3, star_date);
            statement.setTimestamp(4, end_date);
            statement.setInt(5, feature_limit);
            statement.execute();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


}
