package com.edc.users.domain;

import static com.edc.users.domain.UsersUtil.convertToUserDTO;
import static com.edc.users.domain.UsersUtil.getRateForPostion;

import com.edc.users.domain.dto.UserDTO;
import io.vavr.Function1;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UsersFacade {

  private final UsersRepository usersRepository;

  public UserDTO save(UserDTO userDTO) {
    return User.toUserDTO(usersRepository.save(User.fromUserDTO(userDTO)));
  }

  public List<UserDTO> findAll() {
    return convertToUserDTO
        .apply(usersRepository.findAll());
  }

  public List<UserDTO> findAllNotDeleted() {
    return null;
  }

  public List<UserDTO> findAllUsersOfTypeUser() {
    return null;
  }

  public List<UserDTO> findAllNotDeletedUsersOfTypeUser() {
    return null;
  }

  public Tuple2<Integer, Integer> calculateDeletedAndNotDeleted(List<UserDTO> userDTOs) {
    return null;
  }

  public Option<Double> calculateAverageAgeForNotDeletedUsers() {
    return null;
  }

  public Tuple2<List<UserDTO>, List<UserDTO>> filterAdminsAndUsers(Boolean isDeleted,
      String username) {
    return null;
  }

  public List<Tuple2<UUID, Double>> getSalaryMultipliedByPositionRate() {
    return null;
  }
}
