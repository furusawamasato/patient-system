package com.example.patient_system.controller;

import com.example.patient_system.entity.Patient;
import com.example.patient_system.entity.BookingList;
import com.example.patient_system.service.BookingListService;
import com.example.patient_system.service.PatientService;
import com.example.patient_system.form.PatientForm;
import com.example.patient_system.form.BookingListForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final BookingListService bookingListService;

    public PatientController(PatientService patientService, BookingListService bookingListService) {
        this.patientService = patientService;
        this.bookingListService = bookingListService;
    }

// 患者一覧画面
    @GetMapping
    public String patientList(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);

        List<BookingList> bookingLists = patientService.getSelect();
        Map<Long, BookingList> map = new HashMap<>();
        for (BookingList b : bookingLists) {
            map.merge(b.getId(), b, (existing, newVal) -> {
                if (existing.getBookingDate().isBefore(newVal.getBookingDate()) ||
                    (existing.getBookingDate().isEqual(newVal.getBookingDate()) &&
                    existing.getBookingTime().isBefore(newVal.getBookingTime()))) {
                    return newVal;
                }
                return existing;
            });
        }
        model.addAttribute("bookingLists", new ArrayList<>(map.values()));
        return "patient/patient-list";
    }
    // 新規患者登録フォーム表示
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("patientForm", new PatientForm());
        return "patient/patient-form";
    }

    // 新規患者登録
    @PostMapping("/new")
    public String createPatient(PatientForm patientForm) {
        patientService.createPatients(patientForm);
        return "redirect:/patients";
    }

    // 詳細表示
    @GetMapping("/{id}")
    public String patientDetail(@PathVariable long id, Model model) {
        Patient patient = patientService.getPatientsById(id);
        List<BookingList> bookingLists = bookingListService.getBookingListByListId(id);

        model.addAttribute("patients", patient);
        model.addAttribute("bookingLists", bookingLists);
        return "patient/patient-detail";
    }

    // 患者削除
    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable long id) {
        patientService.deletePatients(id);
        return "redirect:/patients";
    }

    // 予約追加フォーム表示
    @GetMapping("/{id}/bookingLists/new")
    public String createBookingListForm(@PathVariable long id, Model model) {
        BookingListForm form = new BookingListForm();
        form.setId(id);
        model.addAttribute("bookingListForm", form);
        return "bookingList/bookingList-form";
    }

    // 予約追加処理
    @PostMapping("/{id}/bookingLists/new")
    public String createBookingList(@PathVariable long id, BookingListForm form) {
        bookingListService.createBookingList(form);
        return "redirect:/patients/" + id;
    }

    // 予約削除
    @PostMapping("/{id}/bookingLists/{bookingId}/delete")
    public String deleteBookingList(@PathVariable long id, @PathVariable long bookingId) {
        bookingListService.deleteBookingList(bookingId);
        return "redirect:/patients/" + id;
    }

// 患者情報の編集画面表示
@GetMapping("/{id}/edit")
public String editPatientInfo(@PathVariable long id, Model model) {
    Patient patient = patientService.getPatientsById(id);
    model.addAttribute("patient", patient);
    return "patient/patient-edit";  // 編集ページ
}

// 患者情報の編集保存
@PostMapping("/{id}/edit")
public String updatePatientInfo(@PathVariable long id, Patient patient) {
    patientService.updatePatient(id, patient);
    return "redirect:/patients/" + id;
}
}