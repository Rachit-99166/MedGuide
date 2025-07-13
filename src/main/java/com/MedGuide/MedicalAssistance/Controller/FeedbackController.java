package com.MedGuide.MedicalAssistance.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.MedGuide.MedicalAssistance.Entity.Feedback;
import com.MedGuide.MedicalAssistance.Repository.FeedbackRepo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackRepo feedbackRepository;

    @GetMapping("/feedback")
    public String feedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @PostMapping("/submit")
    public String submitFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
        return "redirect:/dashboard?feedback=success";
    }
}
