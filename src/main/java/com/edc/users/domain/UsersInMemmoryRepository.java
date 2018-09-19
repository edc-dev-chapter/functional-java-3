package com.edc.users.domain;

import io.vavr.collection.List;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class UsersInMemmoryRepository implements UsersRepository {

  private final ConcurrentHashMap<UUID, User> map = new ConcurrentHashMap<>();

  @Override
  public User save(User user) {
    Objects.requireNonNull(user);

    UUID id = user.getId() == null ? UUID.randomUUID() : user.getId();
    user.setId(id);
    map.put(id, user);

    return user;
  }

  @Override
  public List<User> findAll() {
    return List.of(map.values().toArray(new User[]{}));
  }
}
