package org.example.User_subscriptions;

import org.example.Limits.Limits;

import java.sql.Timestamp;

public class UserSubscriptions {
    private long id;
    private String uuid;
    private Timestamp start_date;
    private Timestamp end_date;
    private Timestamp renewal_date;
    private String status;
    private Timestamp update_at;
    private String updated_by;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public Timestamp getRenewal_date() {
        return renewal_date;
    }

    public void setRenewal_date(Timestamp renewal_date) {
        this.renewal_date = renewal_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
