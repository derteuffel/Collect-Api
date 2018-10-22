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
    private String groupeName;

    @ManyToMany
    private Set<User> users= new HashSet<User>();

    @OneToMany(mappedBy = "groupe")
    private List<These> theses;

    public Groupe(String groupeName) {
        this.groupeName = groupeName;
    }

    public Groupe() {
    }


    public Long getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(Long groupeId) {
        this.groupeId = groupeId;
    }

    public String getGroupeName() {
        return groupeName;
    }

    public void setGroupeName(String groupeName) {
        this.groupeName = groupeName;
    }

   public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<These> getTheses() {
        return theses;
    }

    public void setTheses(List<These> theses) {
        this.theses = theses;
    }
}
