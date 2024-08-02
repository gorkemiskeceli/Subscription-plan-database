package org.example.Plans;

public class Plans {
    private long plans_id;
    private String plan_name;
    private int user_limit;
    private String features;
    private String pricing;

    public Plans(){

    }
    public long getPlans_id() {
        return plans_id;
    }

    public void setPlans_id(long plans_id) {
        this.plans_id = plans_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public int getUser_limit() {
        return user_limit;
    }

    public void setUser_limit(int user_limit) {
        this.user_limit = user_limit;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
    }
}
