package org.example;

import java.sql.Date;
import java.util.List;

public class Playlist {
    private int id;
    private String title;
    private Date creationDate;


    public Playlist(){
    }
    public Playlist(int id, String title, Date creationDate){
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){ return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Playlist { " +
                "title = '" + title +
                "', creation date:='" + creationDate + '\'' +
                '}';
    }

}

