package model;

import java.io.Serializable;

public class Visits implements Serializable{
    private int id;
    private String registrationDate;
    private String date;
    private String time;
    private String comment;
    private int book_id;
    private int client_id;

    public Visits(){
        this.id = -1;
        this.registrationDate = "";
        this.date = "";
        this.time="";
        this.comment = "";
        this.book_id = -1;
        this.client_id = -1;
    }

    public Visits(int id, String registrationDate, String date, String time, String comment, int book_id, int client_id) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.book_id = book_id;
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
}
