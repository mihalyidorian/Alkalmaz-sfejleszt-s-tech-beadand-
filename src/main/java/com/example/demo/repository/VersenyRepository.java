package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entry.VersenyEntry;

public interface VersenyRepository extends JpaRepository<VersenyEntry,Long > {
}
