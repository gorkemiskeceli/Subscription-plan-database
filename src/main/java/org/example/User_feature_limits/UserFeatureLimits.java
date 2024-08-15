package org.example.User_feature_limits;

import java.sql.Timestamp;

public class UserFeatureLimits {
    private long id;
    private String uuid;
    private int limits;
    private Timestamp updated_at;
    private String updated_by;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getUuid(){
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getLimits() {
        return limits;
    }

    public void setLimits(int limits) {
        this.limits = limits;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
