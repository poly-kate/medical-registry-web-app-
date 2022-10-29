package medregistry.controller;

import medregistry.entity.Patient;
import medregistry.entity.User;
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
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;
    private UserService userService;

    @Autowired
    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

    @GetMapping(value = "/add")
    public String showForm(Model model) {
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("patient", new Patient());
        String fieldSearch=null; String textSearch=null;
        model.addAttribute("textSearch", textSearch);
        return "basePatients";
    }


    @GetMapping(value = "/personal")
    public String showPersonal(Model model) {

        String name = userService.getCurrentUser();
        if (name.equals("admin")){
            return "redirect:/appointments/add";
        }
        Patient curPatient = patientService.findById(Integer.parseInt(name));
        model.addAttribute("patient", curPatient);
        return "listAppoint";
    }


    @PostMapping(value = "/add")
    public String addPatient(@ModelAttribute("patient") @Valid Patient patient, BindingResult bindingResult, Model model) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("patients", patientService.findAll());
            return "basePatients";
        }
        try{
            patientService.add(patient);
        }
        catch (Exception e){
            e.getMessage();
        }
        userService.createAndSetUser(Integer.toString(patient.getPolicyNumber()));
        return "redirect:/patients/add";
    }


    //поиск всех пациентов по номеру (фамилии?)
    @PostMapping(value = "/search")
    public String searchPatient(@ModelAttribute("textSearch") String textSearch, Model model)
    {
        try{
            model.addAttribute("patients", patientService.searchIdAndSurname(textSearch));
        }
        catch (Exception e){
            e.getMessage();
        }
        model.addAttribute("patient", new Patient());
        model.addAttribute("textSearch", textSearch);
        return "basePatients";
    }


    @GetMapping("/delete/{policyNumber}")
    public String deletePatient(@PathVariable ("policyNumber") int id, Model model){
        try{
            patientService.deleteById(id);
        }
        catch (Exception e){
            e.getMessage();
        }
        return "redirect:/patients/add";
    }
}
