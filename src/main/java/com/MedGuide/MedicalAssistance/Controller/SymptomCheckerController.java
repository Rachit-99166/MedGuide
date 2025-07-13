package com.MedGuide.MedicalAssistance.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SymptomCheckerController {

    @GetMapping("/symptom-checker")
    public String showPage() {
        return "SymptomChecker";
    }

    @PostMapping("/get-medicines")
    public String getMedicines(@RequestParam String symptoms,
            @RequestParam String started,
            org.springframework.ui.Model model) {

        String result;

        if (symptoms.toLowerCase().contains("fever")) {
            result = "• Paracetamol 500mg - Twice a day\n• Hydration - Drink plenty of water";
        } else if (symptoms.toLowerCase().contains("cough")) {
            result = "• Cough Syrup (Benadryl) - 10ml twice a day\n• Steam inhalation - Twice a day";
        } else if (symptoms.toLowerCase().contains("headache")) {
            result = "• Ibuprofen 400mg - After food if needed\n• Rest in a dark quiet room";
        } else {
            result = "No match found. Please consult a certified doctor.";
        }

        model.addAttribute("result", result);
        return "SymptomChecker";
    }
}
