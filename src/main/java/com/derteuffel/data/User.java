package com.derteuffel.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by derteuffel on 06/10/2018.
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column
    private  String userName;
    @Column
    private String userEmail;
    @Column
    private String userPassword;
    @Column
    private String userCountry;
    @Column
    private String userRegion;
    @Column
    private String userUniversity;
    @Column
    private String userSpeciality;
    @Column
    private String userPhone;
    @Column
    private String userImage;
    @Column
    private Date createdDate= new Date();


    public User() {
    }

    public User(String userName, String userEmail, String userPassword, String userCountry, String userRegion, String userSpeciality) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCountry = userCountry;
        this.userRegion = userRegion;
        this.userSpeciality = userSpeciality;
    }

    public User(String userName, String userEmail, String userPassword, String userCountry,
                String userRegion, String userUniversity, String userSpeciality, String userPhone,
                String userImage) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCountry = userCountry;
        this.userRegion = userRegion;
        this.userUniversity = userUniversity;
        this.userSpeciality = userSpeciality;
        this.userPhone = userPhone;
        this.userImage = userImage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion = userRegion;
    }

    public String getUserUniversity() {
        return userUniversity;
    }

    public void setUserUniversity(String userUniversity) {
        this.userUniversity = userUniversity;
    }

    public String getUserSpeciality() {
        return userSpeciality;
    }

    public void setUserSpeciality(String userSpeciality) {
        this.userSpeciality = userSpeciality;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
