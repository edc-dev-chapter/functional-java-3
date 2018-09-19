package com.edc.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.edc.users.domain.dto.UserDTO;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import org.junit.Before;
import org.junit.Test;

public class CurryingTest {

  private UsersFacade usersFacade;

  @Before
  public void setUp() {
    usersFacade = new UsersConfiguration().usersFacade();
  }

  @Test
  public void shouldFindAllNotDeletedUsersFromList() {
    // given
    List<UserDTO> usersToFilter = usersFacade.findAll();

    // when
    Tuple2<Integer, Integer> deletedAndNotDeleted = usersFacade
        .calculateDeletedAndNotDeleted(usersToFilter);

    // then
    assertThat(deletedAndNotDeleted._1).isEqualTo(2);
    assertThat(deletedAndNotDeleted._2).isEqualTo(3);
  }

  @Test
  public void shouldFindNoDeletedUsersFromList() {
    // setup
    usersFacade
        .findAllNotDeleted().map(userDTO -> userDTO.toBuilder().deleted(true).build())
        .forEach(usersFacade::save);

    // given
    List<UserDTO> usersToFilter = usersFacade.findAll();

    // when
    Tuple2<Integer, Integer> deletedAndNotDeleted = usersFacade.calculateDeletedAndNotDeleted(usersToFilter);

    // then
    assertThat(deletedAndNotDeleted._1).isEqualTo(5);
    assertThat(deletedAndNotDeleted._2).isEqualTo(0);
  }
}
