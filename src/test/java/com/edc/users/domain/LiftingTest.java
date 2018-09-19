package com.edc.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.control.Option;
import org.junit.Before;
import org.junit.Test;

public class LiftingTest {

  private UsersFacade usersFacade;

  @Before
  public void setUp() {
    usersFacade = new UsersConfiguration().usersFacade();
  }

  @Test
  public void shouldCalculateAverageAgeForNotDeletedUsers() {
    // when
    Option<Double> averageAge = usersFacade.calculateAverageAgeForNotDeletedUsers();

    // then
    assertThat(averageAge.isDefined()).isTrue();

    // and
    assertThat(averageAge.get()).isEqualTo(28.0);
  }

  @Test
  public void shouldReturnEmptyOptionalForAverageAgeAsListOfUUIDsIsEmpty() {
    // setup
    usersFacade.findAllNotDeleted()
        .map(userDTO -> userDTO.toBuilder().deleted(true).build())
        .forEach(usersFacade::save);

    // when
    Option<Double> averageAge = usersFacade.calculateAverageAgeForNotDeletedUsers();

    // then
    assertThat(averageAge.isDefined()).isFalse();
  }
  
    @Test
    public void shouldReturnEmptyOptionalForNegativeAgeValue() {
        // setup
        usersFacade.findAllNotDeleted()
                .map(userDTO -> userDTO.toBuilder().age(-10).build())
                .forEach(usersFacade::save);

        // when
        Option<Double> averageAge = usersFacade.calculateAverageAgeForNotDeletedUsers();

        // then
        assertThat(averageAge.isDefined()).isFalse();
    }
    
}
