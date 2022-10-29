package medregistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    /*@GetMapping("/takeAppoint")
    public String takeAppoint(Model model){
        //model.addAttribute("movies", repository.findAll());
        return "takeAppoint";//это html файл
    }
    @GetMapping("/listAppoint")
    public String listAppoint(Model model){
        //model.addAttribute("movies", repository.findAll());
        return "listAppoint";//это html файл
    }
    @GetMapping("/schedule")
    public String schedule(Model model){
        //model.addAttribute("movies", repository.findAll());
        return "schedule";//это html файл
    }*/
    //@GetMapping("/basePatients")
   // public String adminIndex(Model model){
        //model.addAttribute("movies", repository.findAll());
     //   return "basePatients";//это html файл
    //}
}
