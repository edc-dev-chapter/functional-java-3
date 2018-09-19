package com.edc.users.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UsersConfiguration {

  UsersFacade usersFacade() {
    UsersInMemmoryRepository usersInMemmoryRepository = new UsersInMemmoryRepository();

    try {
      commandLineRunner(usersInMemmoryRepository).run();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new UsersFacade(usersInMemmoryRepository);
  }

  @Bean
  UsersFacade usersFacade(UsersRepository usersRepository) {
    return new UsersFacade(usersRepository);
  }

  @Bean
  CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
    return args -> {
      User admin1 = User.builder()
          .age(23)
          .username("admin1")
          .password("admin1_password")
          .salary(1650.0)
          .admin(true)
          .build();

      User admin2 = User.builder()
          .age(18)
          .username("admin2")
          .password("admin2_password")
          .salary(1700.0)
          .admin(true)
          .deleted(true)
          .build();

      User user1 = User.builder()
          .age(16)
          .username("regular_user1")
          .password("regular_user1_password")
          .salary(1200.0)
          .build();

      User user2 = User.builder()
          .age(45)
          .username("regular_user2")
          .password("regular_user2_password")
          .salary(1300.0)
          .build();

      User user3 = User.builder()
          .age(54)
          .username("regular_user3")
          .password("regular_user3_password")
          .salary(1800.0)
          .deleted(true)
          .build();

      usersRepository.save(admin1);
      usersRepository.save(admin2);
      usersRepository.save(user1);
      usersRepository.save(user2);
      usersRepository.save(user3);
    };
  }
}
