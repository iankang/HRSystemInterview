package com.kangethe.hrsystem.repositories;

import com.kangethe.hrsystem.entities.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicsRepository extends JpaRepository<Topics,Long> {
}
