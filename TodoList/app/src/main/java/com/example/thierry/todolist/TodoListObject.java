package com.example.thierry.todolist;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by thierry on 30/03/16.
 */
public class TodoListObject {
        private Date deadline;
    private String action;
    private Boolean status;
    private Integer priorité;

    public TodoListObject(Date deadline, String action, Integer priorité) {
        this.deadline = deadline;
        this.action = action;
        this.priorité = priorité;
        status = true;
    }

    public TodoListObject(String deadline, String action, Integer priorité) {
        this.deadline = ConvertisseurDate.dateFromSlashStr(new SimpleDateFormat("dd/MM/yyyy"),deadline);
        this.action = action;
        this.priorité = priorité;
        status = true;
    }

    public TodoListObject(Date deadline, String action) {
        this.deadline = deadline;
        this.action = action;
        this.priorité = R.integer.priorité_normal;
        status = false;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDeadlineText() {
        return ConvertisseurDate.strSlashFromDate(deadline);
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
