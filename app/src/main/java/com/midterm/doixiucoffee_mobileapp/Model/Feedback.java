package com.midterm.doixiucoffee_mobileapp.Model;

import com.google.firebase.Timestamp;

public class Feedback {
    private String idFeedback;
    private User user;
    private Timestamp date;
    private String content;
    private Boolean incognito;
    private Boolean isPublic;

    public Feedback(String idFeedback, User user, Timestamp date, String content, Boolean incognito, Boolean isPublic) {
        this.idFeedback = idFeedback;
        this.user = user;
        this.date = date;
        this.content = content;
        this.incognito = incognito;
        this.isPublic = isPublic;
    }

    public Feedback(){

    }

    public String getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(String idFeedback) {
        this.idFeedback = idFeedback;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIncognito() {
        return incognito;
    }

    public void setIncognito(Boolean incognito) {
        this.incognito = incognito;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

}
