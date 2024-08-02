package org.example;


import org.example.Config.DataBaseConnectorConfig;
import org.example.Plans.Plans;
import org.example.Plans.PlansRepository;
import org.example.Users.UserPlanMapper;
import org.example.Users.Users;
import org.example.Users.UsersRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        DataBaseConnectorConfig.setConnection();

        UserPlanMapper.createUserPlanTable();
        UserPlanMapper userPlanMapper = new UserPlanMapper();

        long user_id = 1;
        long plans_id = 1;
        Calendar calendar = Calendar.getInstance();
        Timestamp startDate = new Timestamp(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 1);
        Timestamp endDate = new Timestamp(calendar.getTimeInMillis());
        int featureLimit = 4;
        userPlanMapper.saveToUserPlansMapper(user_id, plans_id, startDate, endDate, featureLimit);

        System.out.println("User plan has been saved to the database.");

    }
}