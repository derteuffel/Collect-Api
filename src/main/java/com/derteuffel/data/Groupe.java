package com.derteuffel.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by derteuffel on 21/10/2018.
 */
@Entity
public class Groupe implements Serializable{

    @Id
    @GeneratedValue
    private Long groupeId;
    @Column
    private String groupe;

    @ManyToMany
    @JoinTable(name = "groupe_user", joinColumns = {@JoinColumn(name = "groupe")},
    inverseJoinColumns = {@JoinColumn(name = "user")})
    private Set<User> users= new HashSet<User>();

    @OneToMany(mappedBy = "groupe")
    private ArrayList<These> theses= new ArrayList<These>();

    public Groupe(String groupe) {
        this.groupe = groupe;
    }

    public Groupe() {
    }

    public Long getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(Long groupeId) {
        this.groupeId = groupeId;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public ArrayList<These> getTheses() {
        return theses;
    }

    public void setTheses(ArrayList<These> theses) {
        this.theses = theses;
    }
}
