package com.example.patient_system.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Patient {
    //Id
    private long id;
    //名前
    private String name;
    //年齢
    private Integer age;
    //性別
    private String gender;
    //住所
    private String address;
    //電話
    private String telephone;
    //血液型
    private String bloodType;
    //病名
    private String diseaseName;
    //症状
    private String symptoms;


    private LocalDateTime createdAt;

    private List<BookingList> bookingLists; 

    private String place;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate latestBookingDate;


}
