package medregistry.controller;

import medregistry.entity.Appointment;
import medregistry.entity.Doctor;
import medregistry.enumer.TypeDoctor;
import medregistry.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(value = "/add")
    public String showForm(TypeDoctor typeDoctor, Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("typeDoctor", typeDoctor);
        model.addAttribute("doctor", new Doctor());
        return "baseDoctors";
    }

    @PostMapping(value = "/add")
    public String addDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult bindingResult, TypeDoctor typeDoctor, Model model) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("doctors", doctorService.findAll());
            return "baseDoctors";
        }
        try{
            doctorService.add(doctor);
        }
        catch (Exception e){
            e.getMessage();
        }
        return "redirect:/doctors/add";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable("id") int id, Model model){
        try{
            doctorService.deleteById(id);
        }
        catch (Exception e){
            e.getMessage();
        }
        return "redirect:/doctors/add";
    }

    /*//получаем тип врача - возвращаем список всех врачей такого типа
    @GetMapping("/getByType/{typeDoctor}")
    public String getDoctor(@PathVariable("typeDoctor") TypeDoctor typeDoctor, Model model)
    {
        System.out.println("START CONTROLLER DOC");
        System.out.println(typeDoctor);
        try{
            //doctorService.deleteById(id);
           // model.addAttribute("doctors", doctorService.findByType(typeDoctor));
            System.out.println("RETURN true CONTROLLER DOC");
        }
        catch (Exception e){
            e.getMessage();
        }
        System.out.println("END CONTROLLER DOC");
        return "takeAppoint";
    }*/
}
