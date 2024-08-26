package org.example;


import org.example.Config.DataBaseConnectorConfig;
import org.example.Features.Features;
import org.example.Features.FeaturesRepository;
import org.example.Limits.LimitsRepository;
import org.example.User.User;
import org.example.User_feature_limits.UserFeatureLimitsRepository;
import org.example.User_subscriptions.UserSubscriptionsRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public class Main {
    public static void main(String[] args) {
        DataBaseConnectorConfig.setConnection();
        List<User> nonActiveUsers = UserSubscriptionsRepository.listNonActiveUsers();
        for (User user : nonActiveUsers){
            System.out.println(user);
        }

    }
}