package org.myjournal.dto;

import org.myjournal.entity.Mood;

public class CreatePostDTO {
    
    private String note;

    private Mood mood;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    
}
