package com.tmt.clinic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClinicController {
    private List<Appt> appts = new ArrayList<Appt>();  //datastore
    
    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required=false) String id){
        int index = getIndex(id);
        Appt appointment;
        if(index == Constants.NOT_FOUND){
            appointment = new Appt();
        }else{
            appointment = appts.get(index);
        }
        model.addAttribute("appt", appointment);
        return "form";
    }

    @PostMapping("/submitform")
    public String submitForm(@Valid Appt appt, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) return "form";
        
        String status = Constants.SUCCESS_STATUS;

        int index = getIndex(appt.getId());
        if(index == Constants.NOT_FOUND){
            appts.add(appt);
        }else if(within5days(appt.getDate(), appts.get(index).getDate())){
            //if new/updated date is within 5 days of the original date of the item, update it
            appts.set(index, appt);
        }else{
            //if item exists but updated date is not within 5 days
            status = Constants.FAILED_STATUS;
        }
        //RedirectAttributes temporarily store data like status that survives the redirect and is used in generating the redirected page
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/records";
              
    }

    @GetMapping("/records")
    public String getRecords(Model model){
        model.addAttribute("apptList", appts);
        return "records";
    }

    public int getIndex(String id){
        for(int i=0; i<appts.size(); i++){
            if(appts.get(i).getId().equals(id)){
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    
    public boolean within5days(Date newDate, Date oldDate){
        //returns true if two dates are within 5 days
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;            
    }
}
