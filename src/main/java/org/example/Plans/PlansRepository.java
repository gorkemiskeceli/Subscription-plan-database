package org.example.Plans;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PlansRepository {
    public static void createPlans(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query = "DROP TABLE IF EXISTS plans;"+
            "DROP SEQUENCE IF EXISTS plans_id_seq;"+
            "CREATE SEQUENCE plans_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
            "CREATE TABLE plans("+
            "id INTEGER DEFAULT nextval ('plans_id_seq') NOT NULL,"+
            "name VARCHAR(255) NOT NULL,"+
            "description VARCHAR(255) NOT NULL,"+
            "uuid VARCHAR(255) NOT NULL,"+
            "updated_at TIMESTAMP NOT NULL,"+
            "updated_by VARCHAR(255) NOT NULL,"+
            "CONSTRAINT pk_plans PRIMARY KEY (id)"+
            ");";
            statement.execute(query);
            System.out.println("Plans table has been created at database...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Plans createPlans(Plans plans){
        String query = "INSERT INTO plans(name, description, uuid, updated_at, updated_by) VALUES (?,?,?,?,?)";
            try (PreparedStatement statement = DataBaseConnectorConfig.getConnection().prepareStatement(query)){
                statement.setString(1, plans.getName());
                statement.setString(2, plans.getDescription());
                statement.setString(3, plans.getUuid());
                statement.setTimestamp(4, plans.getUpdated_at());
                statement.setString(5, plans.getUpdated_by());
                statement.executeUpdate();
                System.out.println("Plan" + plans.getName() + "has been created at database...");
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

        return plans;
    }
}
