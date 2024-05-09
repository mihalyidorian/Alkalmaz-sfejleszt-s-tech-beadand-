package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entry.EredmenyEntry;

public interface EredmenyRepository extends JpaRepository<EredmenyEntry,Long>{
    List<EredmenyEntry> findAllByVerseny_VersenyId(Long id);
}
