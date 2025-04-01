package com.examly.springapp.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.entity.Hall;
import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    Logger logger = LoggerFactory.getLogger(HallRepository.class);

    @Query("SELECT b FROM Hall b")
    List<Hall> findAll();

    @Query("SELECT b FROM Hall b WHERE b.id = :id")
    Hall findHallById(Long id);

    default void logRepositoryAction(String action) {
        logger.debug("HallRepository action: {}", action);
    }
}
