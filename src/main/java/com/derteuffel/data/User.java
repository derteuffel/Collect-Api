package com.derteuffel.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.engine.groups.Group;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by derteuffel on 06/10/2018.
 */

@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue
    private Long userId;

    @Column
    @NotNull
    @Size(min = 2)
    private  String name;
    @Column
    @Email
    @NotNull
    private String email;
    @Column
    @NotNull
    @Length(min = 6)
    private String password;
    @NotNull
    @Column
    private String country;
    @Column
    @NotNull
    @Size(min = 3)
    private String region;
    @Column
    @Size(min = 2)
    private String university;
    @Column
    @Size(min = 3)
    private String faculty;
    @Column
    @NotNull
    private String number;
    @Column
    private String img;
    @Column
    private Date createdDate= new Date();
    @Column
    private int active;

    @OneToMany(mappedBy = "user")
    private List<These> theses;

    @OneToOne
    @JoinColumn(name = "role")
    private Role role;

    @ManyToMany
    @JoinTable(name = "groupe_user")
    private Set<Groupe> groupes=new HashSet<Groupe>();


    public User() {
    }

    public User(@NotNull @Size(min = 2) String name, @Email @NotNull String email, @NotNull @Length(min = 6) String password, @NotNull String country,
                @NotNull @Size(min = 3) String region, @NotNull String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.region = region;
        this.number = number;
    }

    public User(@NotNull @Size(min = 2) String name, @Email @NotNull String email, @NotNull @Length(min = 6) String password, @NotNull String country,
                @NotNull @Size(min = 3) String region, @Size(min = 2) String university, @Size(min = 3) String faculty, @NotNull String number, String img, int active ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.region = region;
        this.university = university;
        this.faculty = faculty;
        this.number = number;
        this.img = img;
        this.active=active;
    }

    public User(String name, String email, String password, String country, String region, String university, String number, String faculty, String img) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.region = region;
        this.university = university;
        this.faculty = faculty;
        this.number = number;
        this.img = img;
    }

    public User(User user) {
        this.active= user.getActive();
        this.email=user.getEmail();
        this.name=user.getName();
        this.country=user.getCountry();
        this.region=user.getRegion();
        this.faculty=user.getFaculty();
        this.university=user.getUniversity();
        this.img=user.getImg();
        this.number=user.getNumber();
        this.userId= user.getUserId();
        this.password=user.getPassword();
    }

   public Set<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(Set<Groupe> groupes) {
        this.groupes = groupes;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonIgnore
    public List<These> getTheses() {
        return theses;
    }


    public void setTheses(List<These> theses) {
        this.theses = theses;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
