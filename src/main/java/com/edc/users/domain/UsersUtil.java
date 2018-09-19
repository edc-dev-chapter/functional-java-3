package com.edc.users.domain;

import com.edc.users.domain.dto.UserDTO;
import io.vavr.Function1;
import io.vavr.Function3;
import io.vavr.Function4;
import io.vavr.collection.List;

import java.util.UUID;

class UsersUtil {

  public static final Function1<List<User>, List<UserDTO>> convertToUserDTO =
      users -> users.map(User::toUserDTO);

  public static final Function1<List<UserDTO>, List<UUID>> convertToUUIDs =
      users -> users.map(UserDTO::getId);

  public static final Function1<List<UserDTO>, List<UserDTO>> filterNotDeleted =
      users -> users.filter(user -> !user.isDeleted());

  public static final Function1<List<UserDTO>, List<UserDTO>> filterDeleted =
      users -> users.filter(user -> user.isDeleted());

  public static final Function1<List<UserDTO>, List<UserDTO>> filterTypeUser =
      users -> users.filter(user -> !user.isAdmin());

  public static final Function3<List<UUID>, Function1<List<UserDTO>, List<UserDTO>>, List<UserDTO>, List<UserDTO>> filterByIds =
      (uuids, filters, users) -> filters.apply(users.filter(user -> uuids.contains(user.getId())));

  public static final Function4<List<UserDTO>, Boolean, Boolean, String, List<UserDTO>> filterUsers =
      (users, deleted, isAdmin, username) -> users
          .filter(userDTO -> userDTO.isDeleted() == deleted)
          .filter(userDTO -> userDTO.isAdmin() == isAdmin)
          .filter(userDTO -> userDTO.getUsername().contains(username));

  public static final Function1<List<UserDTO>, Double> calculateAverageAge = (selectedUsers) -> {
    double ageSum = selectedUsers.map(UserDTO::getAge).sum().doubleValue();
    double usersCount = selectedUsers.size();

    if (ageSum <= 0 || usersCount <= 0) {
      throw new IllegalArgumentException("Only positive values are allowed");
    }

    return ageSum / usersCount;
  };

  public static final Function1<Boolean, Integer> getRateForPostion = isAdmin -> {
    Integer rate = 0;
    try {
      Thread.sleep(1000);
      rate = isAdmin ? 3 : 2;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return rate;
  };

}
