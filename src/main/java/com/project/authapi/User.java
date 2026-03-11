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

