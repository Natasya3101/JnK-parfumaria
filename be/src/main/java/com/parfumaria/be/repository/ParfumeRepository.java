package com.parfumaria.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parfumaria.be.model.Parfume;

public interface ParfumeRepository extends JpaRepository<Parfume, Integer> {
}
