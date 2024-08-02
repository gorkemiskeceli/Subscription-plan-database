package org.example.Plans;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlansRepository {

        public static void createPlansTable() {
            try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()) {

                String query = "DROP SEQUENCE IF EXISTS plans_id_seq;" +
                        "CREATE SEQUENCE plans_id_seq INCREMENT BY 1 MINVALUE 0 MAXVALUE 2147483647 START 1;" +
                        "CREATE TABLE IF NOT EXISTS plans(" +
                        "plans_id INTEGER DEFAULT nextval('plans_id_seq') PRIMARY KEY," +
                        "plan_name VARCHAR(255) NOT NULL," +
                        "user_limit INTEGER NOT NULL," +
                        "features VARCHAR(255) NOT NULL,"+
                        "pricing VARCHAR(255) NOT NULL);";

                statement.execute(query);
                System.out.println("Plans Table has been created!!!");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public Plans save(Plans plans){

            String query = "INSERT INTO plans(plan_name, user_limit, features,pricing) VALUES (?,?,?,?)";
            try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
                statement.setString(1, plans.getPlan_name());
                statement.setInt(2, plans.getUser_limit());
                statement.setString(3, plans.getFeatures());
                statement.setString(4, plans.getPricing());

                statement.execute();
                System.out.println("Plan has been created: "+ plans.getPlan_name());
            } catch (SQLException e) {
                throw new RuntimeException("Plan can not be created"+plans.getPlan_name(), e);
            }

            return plans;
        }

}
