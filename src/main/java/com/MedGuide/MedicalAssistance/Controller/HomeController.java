package com.MedGuide.MedicalAssistance.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.MedGuide.MedicalAssistance.Service.GeminiService;
import com.MedGuide.MedicalAssistance.Service.UploadService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GeminiService geminiService;
    private final UploadService uploadService;

    @PostMapping("/ask")
    public String ask(@RequestParam String question, Model model) {
        model.addAttribute("answer", geminiService.ask(question));
        return "dashboard";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("input") MultipartFile file, Model model) {
        try {
            uploadService.saveFile(file);
            model.addAttribute("uploadMessage", "File uploaded successfully!");
        } catch (IOException e) {
            model.addAttribute("uploadMessage", "File upload failed: " + e.getMessage());
        }
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("quote", getRandomQuote());
        return "dashboard";
    }

    private String getRandomQuote() {
        List<String> lst = new ArrayList<>();
        lst.add("An apple a day keeps the doctor away.");
        lst.add("Health is wealth take care of your body, it's the only place you have to live.");
        lst.add("Prevention is better than cure.");
        lst.add("A short walk a day keeps illness at bay.");
        lst.add("Eat healthy, live longer.");
        lst.add("Your body hears everything your mind says think positive.");
        lst.add("Drink more water, stay hydrated, stay energized.");
        lst.add("Sleep is the best medicine.");

        int index = new Random().nextInt(lst.size());
        return lst.get(index);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
