package com.vivid.backend.repository;

import com.vivid.backend.model.Tone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToneRepository extends JpaRepository<Tone, Long> {
  
}
