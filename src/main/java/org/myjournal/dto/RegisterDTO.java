package org.myjournal.dto;


// DTO som används för att ta emot data från frontend till backend när en användare registrerar sig.
public class RegisterDTO {
    
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
