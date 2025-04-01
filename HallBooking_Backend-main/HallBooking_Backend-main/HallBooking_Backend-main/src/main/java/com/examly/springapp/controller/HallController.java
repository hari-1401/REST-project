package com.examly.springapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.entity.Hall;
import com.examly.springapp.service.HallService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/hall")
public class HallController {

    private static final Logger logger = LoggerFactory.getLogger(HallController.class);
    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping
    public ResponseEntity<Object> createHall(@RequestBody Hall hall) {
        logger.info("Creating new hall: {}", hall.getName());
        Hall createdHall = hallService.createHall(hall);
        return ResponseEntity.ok(createdHall);
    }

    @GetMapping
    public ResponseEntity<List<Hall>> getAllHalls() {
        logger.info("Fetching all halls");
        List<Hall> halls = hallService.getAllHall();
        return ResponseEntity.ok(halls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHall(@PathVariable("id") long id) {
        logger.info("Fetching hall with ID: {}", id);
        Hall hall = hallService.getHall(id);
        if (hall == null) {
            logger.warn("Hall not found with ID: {}", id);
            return ResponseEntity.status(404).body("Hall not found with ID: " + id);
        }
        return ResponseEntity.ok(hall);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHall(@PathVariable("id") Long id, @RequestBody Hall hall) {
        logger.info("Updating hall with ID: {}", id);
        hallService.updateHall(id, hall.getName(), hall.getLocation(), hall.getCapacity());
        return ResponseEntity.ok("Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHall(@PathVariable("id") Long id) {
        logger.warn("Deleting hall with ID: {}", id);
        hallService.deleteHall(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @GetMapping("/paginate")
    public Page<Hall> getAllHallsPaginated(Pageable pageable) {
        logger.info("Fetching paginated halls");
        return hallService.getAllPage(pageable);
    }
}
