package com.vivid.backend.repository;

import java.util.Optional;
import java.util.Set;

import com.vivid.backend.model.Dream;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DreamRepository extends JpaRepository<Dream, Long> {

  public Set<Dream> findAllByUserId(Long userId);

  public Optional<Dream> findByUserIdAndId(Long userId, Long dreamId);

}
