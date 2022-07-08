package com.example.romannumerals.web;

import com.example.romannumerals.services.RomanNumeralGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RomanNumeralController {
    private final RomanNumeralGenerator generator;

    public RomanNumeralController(){
        generator = new RomanNumeralGenerator();
    }

    @PostMapping("/convert")
    public String convert(@RequestParam(value = "number") String number, Model model){
        String output;
        try{
            output = generator.parse(number).toString();
        }
        catch (IllegalArgumentException exception){
            try{
                output = generator.generate(Integer.parseInt(number));
            }
            catch (IllegalArgumentException exception1){
                output = "Please enter a valid decimal or roman number between 1 and 3999";
            }
        }
        if(output == null)
            output = "Please enter a valid decimal or roman number between 1 and 3999";
        model.addAttribute("convertedValue",output);
        return "index";
    }
}
