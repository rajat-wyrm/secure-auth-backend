# User: @Indexed(unique=true) on username, BCrypt-hashed password
# User: @Indexed(unique=true) on username, BCrypt-hashed password
# User: @Indexed(unique=true) on username, BCrypt-hashed password
package com.project.authapi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, max = 128)
    private String password;

    public User() {}

