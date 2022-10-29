package medregistry.controller;

import medregistry.entity.Appointment;
import medregistry.entity.Doctor;
import medregistry.entity.Patient;
import medregistry.service.AppointmentService;
import medregistry.service.DoctorService;
import medregistry.service.PatientService;
import medregistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private DoctorService doctorService;
    private PatientService patientService;
    private UserService userService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,  DoctorService doctorService, PatientService patientService, UserService userService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.userService = userService;
    }

    @GetMapping(value = "/add")
    public String showForm(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("appointment", new Appointment());
        return "baseAppointments";
    }

    @GetMapping(value = "/add2")
    public String showForm2(Model model) {

        if (userService.getCurrentUser().equals("admin")){
            return "redirect:/appointments/add";
        }
        model.addAttribute("appointments", appointmentService.findAll());
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("appointment", new Appointment());
        return "takeAppoint";
    }



    @PostMapping(value = "/add")
    public String addAppointment(@ModelAttribute("appointment") @Valid Appointment appointment, BindingResult bindingResult, Model model) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("appointments", appointmentService.findAll());
            return "baseAppointments";
        }
        try{
            appointmentService.add(appointment);
        }
        catch (Exception e){
            e.getMessage();
        }
        return "redirect:/appointments/add";
    }

    @PostMapping(value = "/add2")
    public String addAppointment2(@ModelAttribute("appointment") @Valid Appointment appointment, BindingResult bindingResult) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "baseAppointments";
        }
        String name = userService.getCurrentUser();
        int id = Integer.parseInt(name);
        try{
            Patient curPatient = patientService.findById(id);
            appointment.setPatient(curPatient);
            appointmentService.add(appointment);
        }
        catch (Exception e){
            e.getMessage();
        }
        return "redirect:/appointments/add2";
    }



    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable ("id") int id, Model model){
        try{
            appointmentService.deleteById(id);
        }
        catch (Exception e){
            e.getMessage();
        }
        String name = userService.getCurrentUser();
        if (!name.equals("admin")){
            return "redirect:/patients/personal";//переходи в режим админа принудительно
        }
        return "redirect:/appointments/add";
    }

}
