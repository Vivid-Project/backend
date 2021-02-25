package com.vivid.backend.repository;

import java.util.Optional;

import com.vivid.backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findByToken(String token);

  public Optional<User> findByEmail(String email);

}
