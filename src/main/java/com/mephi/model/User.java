package com.mephi.model;

//import javax.persistence.Entity;
//import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//
//import org.hibernate.annotations.Entity;
//import org.hibernate.annotations.Table;
//import org.hibernate.annotations.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastname;

    @Column(name = "age")
    private Byte age;

    public User(Long _id) {
        this.id = _id;
    }

    public User(){}

    public User(String name, String lastname, Byte age){
        this.age = age;
        this.lastname = lastname;
        this.name = name;
    }

    @Override
    public String toString() {

        return name + " " + lastname + " " + age ;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

//    public Long getId() {
//        return id;
//    }

//    public String getLastname() {
//        return lastname;
//    }

//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }

//    public Byte getAge() {
//        return age;
//    }

//    public void setAge(Byte age) {
//        this.age = age;
//    }

//    public String getName() {
//        return name;
//    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getLastName() {
        return lastname;
    }
}
