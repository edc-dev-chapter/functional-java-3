package com.edc.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.edc.users.domain.dto.UserDTO;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import org.junit.Before;
import org.junit.Test;

public class PartialApplicationTest {

  private UsersFacade usersFacade;

  @Before
  public void setUp() {
    usersFacade = new UsersConfiguration().usersFacade();
  }

  @Test
  public void shouldReturnOneAdminAndOneUserWhenFiltering() {
    // given
    String partOfUsername = "1";
    Boolean isDeleted = false;

    // when
    Tuple2<List<UserDTO>, List<UserDTO>> adminsAndUsers = usersFacade
        .filterAdminsAndUsers(isDeleted, partOfUsername);

    // then
    assertThat(adminsAndUsers._1).hasSize(1);
    assertThat(adminsAndUsers._2).hasSize(1);

    // and
    UserDTO admin = adminsAndUsers._1.get();
    assertThat(admin.isAdmin()).isTrue();
    assertThat(admin.isDeleted()).isEqualTo(isDeleted);
    assertThat(admin.getUsername()).contains(partOfUsername);

    UserDTO user = adminsAndUsers._2.get();
    assertThat(user.isAdmin()).isFalse();
    assertThat(user.isDeleted()).isEqualTo(isDeleted);
    assertThat(user.getUsername()).contains(partOfUsername);
  }

  @Test
  public void shouldReturnNoAdminAndOneUserWhenFiltering() {
    // given
    String partOfUsername = "3";
    Boolean isDeleted = true;

    // when
    Tuple2<List<UserDTO>, List<UserDTO>> adminsAndUsers = usersFacade
        .filterAdminsAndUsers(isDeleted, partOfUsername);

    // then
    assertThat(adminsAndUsers._1).hasSize(0);
    assertThat(adminsAndUsers._2).hasSize(1);

    // and
    UserDTO user = adminsAndUsers._2.get();
    assertThat(user.isAdmin()).isFalse();
    assertThat(user.isDeleted()).isEqualTo(isDeleted);
    assertThat(user.getUsername()).contains(partOfUsername);
  }

  @Test
  public void shouldReturnNoAdminAndNoUserWhenFiltering() {
    // given
    String partOfUsername = "15";
    Boolean isDeleted = true;

    // when
    Tuple2<List<UserDTO>, List<UserDTO>> adminsAndUsers = usersFacade
        .filterAdminsAndUsers(isDeleted, partOfUsername);

    // then
    assertThat(adminsAndUsers._1).hasSize(0);
    assertThat(adminsAndUsers._2).hasSize(0);
  }
}
