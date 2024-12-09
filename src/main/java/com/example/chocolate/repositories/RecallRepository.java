package com.example.chocolate.repositories;

import com.example.chocolate.entities.Recall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecallRepository extends JpaRepository<Recall, Long> {
    List<Recall> findByFinishedProductId(Long finishedProductId);
}
