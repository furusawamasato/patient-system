package com.example.patient_system.repository;

import com.example.patient_system.entity.BookingList;
import com.example.patient_system.entity.Patient;

import com.example.patient_system.mapper.BookingListMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookingListRepository {

    private final BookingListMapper bookingListMapper;
    
    public BookingListRepository(BookingListMapper bookingListMapper) {
        this.bookingListMapper = bookingListMapper;
    }

    public List<BookingList> getBookingListByListId(long id) {
        return bookingListMapper.selectBookingListsById(id);
    }

    public void insertBookingList(BookingList bookingList) {
        bookingListMapper.insertBookingList(bookingList);
    }

    public void deleteBookingList(long bookingId) {
        bookingListMapper.deleteBookingList(bookingId);
    }

    public List<BookingList> selectLatestBookingDate() {
        return bookingListMapper.selectLatestBookingDate();
    }
}
