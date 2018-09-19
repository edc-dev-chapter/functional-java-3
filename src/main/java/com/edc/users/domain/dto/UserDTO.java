package com.edc.users.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

  private UUID id;
  private String username;
  private String password;
  private int age;
  private double salary;
  private boolean admin;
  private boolean deleted;
}
