package org.example;


import org.example.Config.DataBaseConnectorConfig;
import org.example.Features.Features;
import org.example.Features.FeaturesRepository;
import org.example.Limits.LimitsRepository;
import org.example.User_feature_limits.UserFeatureLimitsRepository;
import org.example.User_subscriptions.UserSubscriptionsRepository;

import java.sql.Timestamp;
import java.util.UUID;


public class Main {
    public static void main(String[] args) {
        DataBaseConnectorConfig.setConnection();
        String uuid = UUID.randomUUID().toString();
        long features_id = 1L;
        int user_limits = 10;
        Timestamp updated_at = new Timestamp(System.currentTimeMillis());
        String updated_by = "Gorkem Iskeceli";
        long user_id = 1L;
        UserFeatureLimitsRepository userFeatureLimitsRepository = new UserFeatureLimitsRepository();
        userFeatureLimitsRepository.createUserFeatureLimit(uuid, features_id, user_limits, updated_at, updated_by, user_id);


    }
}