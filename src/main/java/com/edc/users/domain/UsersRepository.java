package com.edc.users.domain;

import io.vavr.collection.List;
import org.springframework.data.repository.Repository;

import java.util.UUID;

interface UsersRepository extends Repository<User, UUID> {

  User save(User user);

  List<User> findAll();
}
