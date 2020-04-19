/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internal.entity;

import java.io.Serializable;

import javax.persistence.*;
/**
 * Database class for user.
 * @author TheProthean
 *
 */
@Entity(name="User")
@Table(name="users")
public class User implements Serializable {
    @Id
    private Long id;
    private String name;
    private String password;
    private Integer role;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    public User() {
        
    }
    
    public User(String name) {
        this.name = name;
    }
    
    public User(String name, String password, int role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
    
    public User(Long id, String name, String password, int role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    /**
     * @return the role
     */
    public Integer getRole() {
        return role;
    }
}
