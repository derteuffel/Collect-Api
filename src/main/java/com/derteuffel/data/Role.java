package com.derteuffel.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by derteuffel on 20/10/2018.
 */
@Entity
public class Role {

 @Id
 @GeneratedValue
private Long roleId;

    private String role;

    @OneToOne(mappedBy = "role")
    private User user;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
