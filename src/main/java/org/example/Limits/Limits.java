package org.example.Limits;

import java.sql.Timestamp;

public class Limits {
    private long id;
    private int limit_value;
    private Timestamp updated_at;
    private String updated_by;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLimit() {
        return limit_value;
    }

    public void setLimit_value(int limit_value) {
        this.limit_value = limit_value;
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
