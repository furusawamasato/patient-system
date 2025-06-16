package com.example.patient_system.mapper;

import com.example.patient_system.entity.BookingList;
import com.example.patient_system.entity.Patient;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface BookingListMapper {
    @Select("SELECT * FROM bookingLists WHERE id = #{id}")
    List<BookingList> selectBookingListsById(long id); 

    @Insert("INSERT INTO bookingLists (treatmentDetails, bookingDate, bookingTime, place, id) VALUES (#{treatmentDetails}, #{bookingDate}, #{bookingTime}, #{place}, #{id})")
    @Options(useGeneratedKeys=true, keyProperty="bookingId")
    void insertBookingList(BookingList bookingList);

    @Delete("DELETE FROM bookingLists WHERE booking_id = #{bookingId}")
    void deleteBookingList(long bookingId);

    @Select("SELECT * FROM bookingLists ORDER BY bookingDate DESC")
    List<BookingList> selectLatestBookingDate();

    
}
