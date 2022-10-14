package com.tmt.clinic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClinicController {
    
    @GetMapping("/")
    public String getForm(){
        return "form";
    }

    @PostMapping("/submitform")
    public String submitForm(){
        return "redirect:/records";
    }

    @GetMapping("/records")
    public String getRecords(){
        return "records";
    }
}
