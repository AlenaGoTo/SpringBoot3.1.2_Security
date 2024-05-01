package ru.itmentor.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


// UserDetails хранит основную информацию о пользователе, которая позже инкапсулируется в Authentication объекты
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "lastname")
   private String lastName;

   @Column(name = "age")
   private Byte age;

   @Column(name = "username", nullable = false, unique = true)
   private String username;

   @Column(name = "password")
   private String password;

   @Column(name = "enabled")
   private boolean enabled = true;

   @ManyToMany(cascade = {
           CascadeType.PERSIST,
           CascadeType.MERGE })
   @JoinColumn(name = "user_id")
   private List<Role> roles = new ArrayList<>();

   public User() {}
   
   public User(String firstName, String lastName, Byte age, String username, String password) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
      this.username = username;
      this.password = password;
   }

   public List<Role> getRoles() {
      return roles;
   }
   public void setRoles(List<Role> roles) {
      this.roles = roles;
   }
   public void addRole(Role role) {
      this.roles.add(role);
      //role.getUsers().add(this);
   }
   public void removeRole(Role role) {
      this.roles.remove(role);
      //role.getUsers().remove(this);
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
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "ID - " + id + ", NAME - " + firstName + ", LASTNAME - " + lastName + ", AGE - " + age;
   }

   // НЕистекший срок действия УЗ
   @Override
   public boolean isAccountNonExpired() {
      return false;
   }
   // НЕзаблокированный пользователь
   @Override
   public boolean isAccountNonLocked() {
      return false;
   }
   // НЕистекший пароль
   @Override
   public boolean isCredentialsNonExpired() {
      return false;
   }
   // Включен ли пользователь
   @Override
   public boolean isEnabled() {
      return false;
   }
   // полномочия, предоставленные пользователю
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

}
