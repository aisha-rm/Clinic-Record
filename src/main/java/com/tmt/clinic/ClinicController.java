package com.tmt.clinic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClinicController {
    List<Appt> appts = new ArrayList<Appt>();
    
    @GetMapping("/")
    public String getForm(Model model){
        model.addAttribute("appt", new Appt());
        return "form";
    }

    @PostMapping("/submitform")
    public String submitForm(Appt appt){
        appts.add(appt);
        return "redirect:/records";
    }

    @GetMapping("/records")
    public String getRecords(Model model){
        model.addAttribute("apptList", appts);
        return "records";
    }
}
