package com.edc.users.domain;

import com.edc.users.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String username;
  private String password;
  private int age;
  private double salary;
  private boolean admin;
  private boolean deleted;

  public static User fromUserDTO(UserDTO userDTO) {
    return User.builder()
        .id(userDTO.getId())
        .username(userDTO.getUsername())
        .password(userDTO.getPassword())
        .age(userDTO.getAge())
        .salary(userDTO.getSalary())
        .admin(userDTO.isAdmin())
        .deleted(userDTO.isDeleted())
        .build();
  }

  public static UserDTO toUserDTO(User user) {
    return UserDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .age(user.getAge())
        .salary(user.getSalary())
        .admin(user.isAdmin())
        .deleted(user.isDeleted())
        .build();
  }
}
