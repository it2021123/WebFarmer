package com.example.eagrotis.payload.request;

import com.example.eagrotis.entity.Application;
import jakarta.validation.constraints.Size;

public class CheckRequest {

    @Size(max = 200)
    private String comments;

    private Application.statusEnum status;

    private String employee;

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Application.statusEnum getStatus() {
        return status;
    }

    public void setStatus(Application.statusEnum status) {
        this.status = status;
    }

}
