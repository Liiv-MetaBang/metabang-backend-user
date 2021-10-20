package com.dkne.metabang.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(length = 45, nullable = false)
    private String user_name;

    @Column(length = 45, nullable = false)
    private String password;

    @Column(length = 45, nullable = true)
    private String account;
    
    @Column(length = 45, nullable = true)
    private String email;
    
    @Column(nullable = false)
    private int role;
    
    @Builder
    public User(String user_name, String password, String account, String email, int role) {
        this.user_name = user_name;
        this.password = password;
        this.account = account;
        this.email = email;
        this.role = role;
    }

    
    public User update(String account , String email) {
        this.account = account;
    	this.email = email;
        return this;
    }
    
}
