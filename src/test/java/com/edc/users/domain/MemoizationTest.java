package com.edc.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class MemoizationTest {

  private UsersFacade usersFacade;

  @Before
  public void setUp() {
    usersFacade = new UsersConfiguration().usersFacade();
  }

  @Test(timeout=3000)
  public void getSalaryMultipliedByPositionRate() {
    // when
    List<Tuple2<UUID, Double>> salaryMultipliedByPositionRate = usersFacade
        .getSalaryMultipliedByPositionRate();

    // then
    assertThat(salaryMultipliedByPositionRate).isNotEmpty();

    // and
    assertThat(salaryMultipliedByPositionRate.size()).isGreaterThan(0);
    assertThat(salaryMultipliedByPositionRate.map(Tuple2::_2).sum()).isEqualTo(18650.0);
  }
}
