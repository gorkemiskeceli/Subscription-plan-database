package org.example.Plans;

import org.example.Config.DataBaseConnectorConfig;

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
}
