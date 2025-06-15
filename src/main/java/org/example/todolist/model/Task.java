package org.example.todolist.model;

public class Task {
    private int id;
    private String title;
    private String deadline;
    private boolean isDone;
    private int userID;

    public Task(){}

    public Task(int id, String title, String deadline, boolean isDone, int userID){
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.isDone = isDone;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
