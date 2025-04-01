package com.examly.springapp.entity;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "halls")
public class Hall {

    private static final Logger logger = LoggerFactory.getLogger(Hall.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    private String location;
    private int capacity;

    public Hall() {
        logger.info("Hall entity created");
    }

    public long getId() {
        logger.debug("Getting Hall ID: {}", id);
        return id;
    }

    public void setId(long id) {
        logger.debug("Setting Hall ID: {}", id);
        this.id = id;
    }

    public String getName() {
        logger.debug("Getting Hall Name: {}", name);
        return name;
    }

    public void setName(String name) {
        logger.debug("Setting Hall Name: {}", name);
        this.name = name;
    }

    public String getLocation() {
        logger.debug("Getting Hall Location: {}", location);
        return location;
    }

    public void setLocation(String location) {
        logger.debug("Setting Hall Location: {}", location);
        this.location = location;
    }

    public int getCapacity() {
        logger.debug("Getting Hall Capacity: {}", capacity);
        return capacity;
    }

    public void setCapacity(int capacity) {
        logger.debug("Setting Hall Capacity: {}", capacity);
        this.capacity = capacity;
    }
}
