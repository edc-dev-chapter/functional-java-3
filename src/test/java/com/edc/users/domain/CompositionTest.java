package com.edc.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.edc.users.domain.dto.UserDTO;
import io.vavr.collection.List;
import org.junit.Before;
import org.junit.Test;

public class CompositionTest {

  private UsersFacade usersFacade;

  @Before
  public void setUp() {
    usersFacade = new UsersConfiguration().usersFacade();
  }

  @Test // andThen
  public void shouldFindAllNotDeleted() {
    // when
    List<UserDTO> users = usersFacade.findAllNotDeleted();

    // then
    assertThat(users.size()).isEqualTo(3);

    // and
    assertThat(users.filter(user -> user.isAdmin()).size()).isEqualTo(1);
    assertThat(users.filter(user -> !user.isAdmin()).size()).isEqualTo(2);
    assertThat(users.filter(user -> !user.isDeleted()).size()).isEqualTo(3);
  }

  @Test // andThen
  public void shouldFindAllOfTypeUser() {
    // when
    List<UserDTO> users = usersFacade.findAllUsersOfTypeUser();

    // then
    assertThat(users.size()).isEqualTo(3);

    // and
    assertThat(users.filter(user -> user.isAdmin()).size()).isEqualTo(0);
  }

  @Test // compose
  public void shouldFindAllNotDeletedUsers() {
    // when
    List<UserDTO> users = usersFacade.findAllNotDeletedUsersOfTypeUser();

    // then
    assertThat(users.size()).isEqualTo(2);

    // and
    assertThat(users.filter(user -> user.isAdmin()).size()).isEqualTo(0);
    assertThat(users.filter(user -> !user.isAdmin()).size()).isEqualTo(2);
  }
}
