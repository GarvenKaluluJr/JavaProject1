package com.project1.taskapi.model;

import jakarta.persistence.*;
<<<<<<< Updated upstream
import org.hibernate.annotations.GenericGenerator;

=======
>>>>>>> Stashed changes
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
<<<<<<< Updated upstream
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
=======
>>>>>>> Stashed changes
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

<<<<<<< Updated upstream
    // Getters and setters
=======
    public User() {}

    public User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

>>>>>>> Stashed changes
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
