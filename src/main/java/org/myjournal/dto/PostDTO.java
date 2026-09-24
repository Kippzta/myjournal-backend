package org.myjournal.dto;

import java.time.LocalDateTime;

import org.myjournal.entity.Mood;
import org.myjournal.entity.Post;

public class PostDTO {

    private Long id;

    private String note;

    private LocalDateTime createdAt;

    private Mood mood;

    private UserDTO userDto;

    public PostDTO(Post post) {
        this.id = id;
        this.note = note;
        this.createdAt = LocalDateTime.now();
        this.mood = mood;
        this.userDto = userDto;
    }

    public Long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Mood getMood() {
        return mood;
    }


    public UserDTO getUserDto() {
        return userDto;
    }
    
}
