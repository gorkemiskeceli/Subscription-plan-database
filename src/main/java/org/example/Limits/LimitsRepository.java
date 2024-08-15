package org.example.Limits;

import org.example.Config.DataBaseConnectorConfig;

import java.sql.SQLException;
import java.sql.Statement;

public class LimitsRepository {
    public static void createLimits(){
        try (Statement statement = DataBaseConnectorConfig.getConnection().createStatement()){
            String query = "DROP TABLE IF EXISTS limits;"+
                    "DROP SEQUENCE IF EXISTS limits_id_seq;"+
                    "CREATE SEQUENCE limits_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;"+
                    "CREATE TABLE limits("+
                    "id INTEGER DEFAULT nextval('limits_id_seq') NOT NULL,"+
                    "plan_id INTEGER NOT NULL,"+
                    "feature_id INTEGER NOT NULL,"+
                    "updated_at TIMESTAMP NOT NULL,"+
                    "updated_by VARCHAR(255) NOT NULL,"+
                    "CONSTRAINT limits_feature_id_fkey FOREIGN KEY (feature_id) REFERENCES features(id) NOT DEFERRABLE,"+
                    "CONSTRAINT limits_plant_id_fkey FOREIGN KEY (plan_id) REFERENCES plans(id) NOT DEFERRABLE"+
                    ");";
            statement.execute(query);
            System.out.println("Limits table has been created in the database...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
