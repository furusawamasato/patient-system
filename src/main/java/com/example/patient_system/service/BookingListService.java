package com.example.patient_system.service;

import com.example.patient_system.repository.BookingListRepository;
import com.example.patient_system.repository.PatientRepository;
import com.example.patient_system.entity.BookingList;
import com.example.patient_system.form.BookingListForm;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingListService {
    private final BookingListRepository bookingListRepository;
    
    public BookingListService(BookingListRepository bookingListRepository) {
        this.bookingListRepository = bookingListRepository;
    }

    public List<BookingList> getBookingListByListId(long id) {
        return bookingListRepository.getBookingListByListId(id);
    }

    public void createBookingList(BookingListForm bookingListForm) {
        BookingList bookingList = new BookingList();
        bookingList.setTreatmentDetails(bookingListForm.getTreatmentDetails());
        bookingList.setBookingDate(bookingListForm.getBookingDate());
        bookingList.setBookingTime(bookingListForm.getBookingTime());
        bookingList.setPlace(bookingListForm.getPlace());
        bookingList.setId(bookingListForm.getId());
        bookingListRepository.insertBookingList(bookingList);
    }

    public void deleteBookingList(long bookingId) {
        bookingListRepository.deleteBookingList(bookingId);
    }

    public List<BookingList> selectLatestBookingDate() {
        return bookingListRepository.selectLatestBookingDate();
    }
}
