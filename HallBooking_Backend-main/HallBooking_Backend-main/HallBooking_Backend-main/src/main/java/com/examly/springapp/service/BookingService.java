package com.examly.springapp.service;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.entity.Hall;
import com.examly.springapp.entity.User;
import com.examly.springapp.repository.BookRepository;
import com.examly.springapp.repository.HallRepository;
import com.examly.springapp.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final HallRepository hallRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    public BookingService(BookRepository bookRepository,UserRepository userRepository,HallRepository hallRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.hallRepository = hallRepository;
    }

    public Booking createBooking(Booking booking) {
      // Fetch and assign the User
      User user = userRepository.findById(booking.getUser().getId())
              .orElseThrow(() -> new RuntimeException("User not found"));
      booking.setUser(user);

      // Fetch and assign the Hall
      Hall hall = hallRepository.findById(booking.getHall().getId())
              .orElseThrow(() -> new RuntimeException("Hall not found"));
      booking.setHall(hall);

      return bookRepository.save(booking);
  }

    public List<Booking> getAllBookings() {
        return bookRepository.findAll();
    }

    public Booking getBooking(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));
    }

    public Booking updateBooking(Long id, User user, Hall hall, LocalDate bookingDate) {
        Booking booking = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));

        if (user != null) {
            booking.setUser(user);
        }
        if (hall != null) {
            booking.setHall(hall);
        }
        if (bookingDate != null) {
            booking.setBookingDate(bookingDate);
        }

        return bookRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Booking not found: " + id);
        }
        bookRepository.deleteById(id);
    }

    public Page<Booking> getAllBookings(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
