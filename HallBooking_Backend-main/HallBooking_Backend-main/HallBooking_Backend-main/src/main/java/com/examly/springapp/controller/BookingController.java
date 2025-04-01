package com.examly.springapp.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.service.BookingService;

@RestController
@RequestMapping("/book")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBooking(@PathVariable("id") Long id) {
        Booking booking = bookingService.getBooking(id);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable("id") Long id, @RequestBody Booking booking) {
        bookingService.updateBooking(id, booking.getUser(), booking.getHall(), booking.getBookingDate());
        return ResponseEntity.ok("Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<Booking>> getAllPaginatedBookings(Pageable pageable) {
        Page<Booking> bookings = bookingService.getAllBookings(pageable);
        return ResponseEntity.ok(bookings);
    }
}
