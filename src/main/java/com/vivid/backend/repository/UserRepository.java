package com.vivid.backend.repository;

import com.vivid.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {

}
