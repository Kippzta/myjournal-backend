package org.myjournal.dto;

import org.myjournal.entity.User;

//DTO som används i PostDTO för att skicka med information om användaren som skapade inlägget utan att lösenordet läcker ut.
public class UserDTO {

    private Long id;

    private String username;

    private String role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }


    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getRole() {
        return role;
    }

    


    
}
