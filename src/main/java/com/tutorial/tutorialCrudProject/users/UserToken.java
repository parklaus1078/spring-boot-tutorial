package com.tutorial.tutorialCrudProject.users;

import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;

@Getter
@Setter
public class UserToken {
    private String userToken;
    private String email;
    private String contact;

    public UserToken (User user) {
        this.email = user.getEmail();
        this.contact = user.getContact();
        this.userToken = generateToken(user.getEmail());
    }

        private String generateToken(String email) {
        // https://github.com/jwtk/jjwt?tab=readme-ov-file#quickstart
        final SecretKey secretKey = Jwts.SIG.HS256.key().build();
        final String jwt = Jwts.builder().subject(email).signWith(secretKey).compact();

        return jwt;
    }


}
