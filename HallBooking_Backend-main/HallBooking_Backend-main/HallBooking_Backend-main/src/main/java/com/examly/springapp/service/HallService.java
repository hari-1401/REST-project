package com.examly.springapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.examly.springapp.entity.Hall;
import com.examly.springapp.repository.HallRepository;
import java.util.List;

@Service
public class HallService {

    private static final Logger logger = LoggerFactory.getLogger(HallService.class);
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Hall createHall(Hall hall) {
        logger.info("Saving new hall: {}", hall.getName());
        return hallRepository.save(hall);
    }

    public List<Hall> getAllHall() {
        logger.info("Retrieving all halls from database");
        return hallRepository.findAll();
    }

    public Hall getHall(Long id) {
        logger.info("Fetching hall with ID: {}", id);
        return hallRepository.findById(id).orElse(null);
    }

    public Hall updateHall(long id, String name, String location, int capacity) {
        logger.info("Updating hall with ID: {}", id);
        Hall hall = hallRepository.findById(id).orElseThrow(() -> {
            logger.error("Hall not found with ID: {}", id);
            return new RuntimeException("Hall not found: " + id);
        });

        if (name != null) {
            hall.setName(name);
        }
        if (location != null) {
            hall.setLocation(location);
        }
        if (capacity != 0) {
            hall.setCapacity(capacity);
        }
        return hallRepository.save(hall);
    }

    public void deleteHall(Long id) {
        logger.warn("Deleting hall with ID: {}", id);
        if (!hallRepository.existsById(id)) {
            logger.error("Hall not found with ID: {}", id);
            throw new RuntimeException("Hall not found: " + id);
        }
        hallRepository.deleteById(id);
    }

    public Page<Hall> getAllPage(Pageable pageable) {
        logger.info("Fetching paginated halls");
        return hallRepository.findAll(pageable);
    }
}
