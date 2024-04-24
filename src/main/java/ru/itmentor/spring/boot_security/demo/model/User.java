package ru.itmentor.spring.boot_security.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS_WEB")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "lastname")
   private String lastName;

   @Column(name = "age")
   private Byte age;

   public User() {}
   
   public User(String firstName, String lastName, Byte age) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public Byte getAge() {
      return age;
   }

   public void setAge(Byte age) {
      this.age = age;
   }

   @Override
   public String toString() {
      return "ID - " + id + ", NAME - " + firstName + ", LASTNAME - " + lastName + ", AGE - " + age;
   }

}
