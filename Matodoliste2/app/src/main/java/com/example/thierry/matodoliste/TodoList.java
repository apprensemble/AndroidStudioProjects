package com.example.thierry.matodoliste;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by thierry on 14/03/16.
 */
public class TodoList {
    private Date deadline;
    private String action;
    private Boolean status;
    private Integer priorité;

    public TodoList(Date deadline, String action, Integer priorité) {
        this.deadline = deadline;
        this.action = action;
        this.priorité = priorité;
        status = true;
    }

    public TodoList(Date deadline, String action) {
        this.deadline = deadline;
        this.action = action;
        this.priorité = R.integer.priorité_normal;
        status = false;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getPriorité() {
        return priorité;
    }

    public void setPriorité(Integer priorité) {
        this.priorité = priorité;
    }

}
