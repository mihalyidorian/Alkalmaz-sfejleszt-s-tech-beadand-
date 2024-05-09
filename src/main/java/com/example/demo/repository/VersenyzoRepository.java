package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entry.VersenyzoEntry;

public interface VersenyzoRepository extends JpaRepository<VersenyzoEntry,Long > {
}
