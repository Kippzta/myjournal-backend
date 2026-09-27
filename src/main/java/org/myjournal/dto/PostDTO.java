package org.myjournal.dto;

import java.time.LocalDateTime;

import org.myjournal.entity.Mood;
import org.myjournal.entity.Post;
//Dto skapad egentligen bara för att userDTO ska kunna skapas och skickas till frontend utan att lösenordet läcker ut.
//Pga att Post innehåller en User med lösenordet så skapar vi en säker kopia som innehåller en UserDTO istället för en User.
public class PostDTO {

    private Long id;

    private String note;

    private LocalDateTime createdAt;

    private Mood mood;

    private UserDTO userDto;

    
    public PostDTO(Post post) {
        this.id = post.getId();
        this.note = post.getNote();
        this.createdAt = post.getCreatedAt();
        this.mood = post.getMood();
        this.userDto = new UserDTO(post.getUser());
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
