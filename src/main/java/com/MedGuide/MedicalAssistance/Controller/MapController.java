package com.MedGuide.MedicalAssistance.Controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapController {

    Map<String, String> mapParams = Map.ofEntries(
            Map.entry("delhi", "bbox=77.080,28.600,77.250,28.750&marker=28.650,77.170"),
            Map.entry("mumbai", "bbox=72.820,18.920,72.890,18.980&marker=18.950,72.860"),
            Map.entry("chennai", "bbox=80.220,13.000,80.270,13.050&marker=13.025,80.245"),
            Map.entry("kolkata", "bbox=88.310,22.540,88.370,22.600&marker=22.570,88.340"),
            Map.entry("bangalore", "bbox=77.580,12.950,77.620,12.990&marker=12.970,77.600"),
            Map.entry("hyderabad", "bbox=78.460,17.370,78.510,17.430&marker=17.400,78.485"),
            Map.entry("ahmedabad", "bbox=72.550,23.000,72.630,23.080&marker=23.040,72.590"),
            Map.entry("pune", "bbox=73.790,18.490,73.880,18.570&marker=18.530,73.830"),
            Map.entry("jaipur", "bbox=75.740,26.860,75.860,26.980&marker=26.920,75.800"),
            Map.entry("lucknow", "bbox=80.900,26.790,81.000,26.890&marker=26.840,80.950"),
            Map.entry("vellore", "bbox=79.120,12.910,79.160,12.960&marker=12.935,79.140")
    );

    @GetMapping("/map")
    public String showMap(@RequestParam(required = false) String location, Model model) {
        if (location != null && mapParams.containsKey(location.toLowerCase())) {
            String params = mapParams.get(location.toLowerCase());
            String mapUrl = "https://www.openstreetmap.org/export/embed.html?" + params + "&layer=mapnik";
            model.addAttribute("mapUrl", mapUrl);
            model.addAttribute("location", location);
        } else if (location != null) {
            model.addAttribute("error", "Location not supported. Try: mumbai, delhi, bangalore.");
        }
        return "Map";
    }

    @GetMapping("/Emergency")
    public String showEmergencyPage() {
        return "Map";
    }

    @PostMapping("/Emergency")
    public String handleConfirmation(@RequestParam(required = false) String confirm, Model model) {
        model.addAttribute("confirm", confirm);
        return "Map";
    }
}
