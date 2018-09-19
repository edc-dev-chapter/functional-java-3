package com.edc.users.infrastructure.web.controllers;

import com.edc.users.domain.UsersFacade;
import com.edc.users.domain.dto.UserDTO;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("users")
class UsersController {

  private final UsersFacade usersFacade;

  @PostMapping
  public UserDTO save(@RequestBody UserDTO userDTO) {
    return usersFacade.save(userDTO);
  }

  @GetMapping
  public List<UserDTO> findAll() {
    return usersFacade.findAll();
  }

  @GetMapping("/not-deleted")
  public List<UserDTO> findAllNotDeleted() {
    return usersFacade.findAllNotDeleted();
  }

  @GetMapping("/of-type-user")
  public List<UserDTO> findAllUsersOfTypeUser() {
    return usersFacade.findAllUsersOfTypeUser();
  }

  @GetMapping("/not-deleted-of-type-user")
  public List<UserDTO> findAllNotDeletedUsersOfTypeUser() {
    return usersFacade.findAllNotDeletedUsersOfTypeUser();
  }

  @GetMapping("/deleted-and-not-deleted")
  public Tuple2<Integer, Integer> calculateDeletedAndNotDeleted() {
    List<UserDTO> allusers = usersFacade.findAll();
    return usersFacade.calculateDeletedAndNotDeleted(allusers);
  }

  @GetMapping("/average-age")
  public Double calculateAverageAgeForNotDeletedUsers() {
    return usersFacade.calculateAverageAgeForNotDeletedUsers().getOrElse(0.0);
  }

  @GetMapping("/filter/deleted/{isDeleted}/username/{username}")
  public Tuple2<List<UserDTO>, List<UserDTO>> filterAdminsAndUsers(@PathVariable Boolean isDeleted,
      @PathVariable String username) {
    return usersFacade.filterAdminsAndUsers(isDeleted, username);
  }

  @GetMapping("/rated-salary")
  public java.util.List<Tuple2<UUID, Double>> getSalaryMultipliedByPositionRate() {
    return usersFacade.getSalaryMultipliedByPositionRate().asJava();
  }
}
