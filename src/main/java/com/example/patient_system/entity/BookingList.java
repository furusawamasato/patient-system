package com.example.patient_system.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookingList {
    //ID
    private long id;
    //予約ID
    private long bookingId;
    //診療内容
    private String treatmentDetails;
    //予約日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;
    //予約時間
    private LocalTime bookingTime;
    //場所
    private String place;

    private Patient patient;

    private LocalDate latestBookingDate;
}
