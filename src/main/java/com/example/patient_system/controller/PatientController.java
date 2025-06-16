package com.example.patient_system.controller;

import com.example.patient_system.entity.Patient;
import com.example.patient_system.entity.BookingList;
import com.example.patient_system.service.BookingListService;
import com.example.patient_system.service.PatientService;
import com.example.patient_system.form.PatientForm;
import com.example.patient_system.form.BookingListForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.expression.Lists;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    private final BookingListService bookingListService;

    public  PatientController(PatientService patientService, BookingListService bookingListService) {
        this.patientService = patientService;
        this.bookingListService = bookingListService;
    }

    //基礎画面
    @GetMapping
    public String patientList(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);

        List<BookingList> bookingLists = bookingListService.selectLatestBookingDate();
        Map<Long, BookingList> map = new HashMap<>();
        for (BookingList bookingList : bookingLists) {
            if (map.get(bookingList.getId()) == null) {
                map.put(bookingList.getId(), bookingList);
            }

            else if (map.get(bookingList.getId()).getBookingDate().compareTo(bookingList.getBookingDate()) < 0) {
                map.put(bookingList.getId(), bookingList);
            }

            else if (map.get(bookingList.getId()).getBookingDate().compareTo(bookingList.getBookingDate()) == 0) {
                if (map.get(bookingList.getId()).getBookingTime().compareTo(bookingList.getBookingTime()) < 0) {
                    map.put(bookingList.getId(), bookingList);
                }
            }
        }

        bookingLists = new ArrayList<>(map.values());
        model.addAttribute("bookingLists", bookingLists);

        return "patient/patient-list";
    }

    //基礎画面(追加)
    @GetMapping("/new")
    public String showForm(Model model) {
        PatientForm patientForm = new PatientForm();
        model.addAttribute("patientForm", patientForm);
        return "patient/patient-form";
    }

    @PostMapping("/new")
    public String createLists(PatientForm patientForm) {
        patientService.createPatients(patientForm);
        
        return "redirect:/patients";
    }


    //詳細画面
    @GetMapping("/{id}")
    public String lists(@PathVariable long id, Model model) {
        Patient patients = patientService.getPatientsById(id);
        model.addAttribute("patients", patients);

        List<BookingList> bookingLists = bookingListService.getBookingListByListId(id);
        model.addAttribute("bookingLists", bookingLists);
        return "patient/patient-detail";
    }


    //基本画面(削除)
    @PostMapping("/{id}/delete")
    public String deleteLists(@PathVariable long id) {
        patientService.deletePatients(id);
        return "redirect:/patients";
    }

    //詳細画面(追加)
    @GetMapping("/{id}/bookingLists/new")
    public String createBookingListForm(@PathVariable long id, Model model) {
        BookingListForm bookingListForm = new BookingListForm();
        bookingListForm.setId(id);
        model.addAttribute("bookingListForm", bookingListForm);

        return "bookingList/bookingList-form";
    }

    @PostMapping("/{id}/bookingLists/new")
        public String createBookingList(@PathVariable long id, BookingListForm bookingListForm) {
        bookingListService.createBookingList(bookingListForm);
        return "redirect:/patients/" + id;
    }


    //詳細画面(削除)
    @PostMapping("/{id}/bookingLists/{bookingId}/delete")
    public String deleteBookingList(@PathVariable long id, @PathVariable long bookingId) {
        bookingListService.deleteBookingList(bookingId);
        return "redirect:/patients/" + id;
    }


    // //詳細画面(編集)
    // @GetMapping("/{id}/bookingLists/{bookingId}/edit")
    // public String editPatient(@PathVariable long id, @PathVariable long bookingId, Model model) {
    //     Patient patient = patientService.getPatientsById(id);

    //     model.addAttribute("patient", patient);

    //     return "patient/patient-edit";
    // }

    // @PostMapping("/{id}/bookingLists/{bookingId}/edit")
    // public String updatePatient(@PathVariable long id, @PathVariable long bookingId, Patient patient) {
    //     patientService.updatePatient(id, patient);
    //     return "redirect:/patients" + id;
    // }
}
