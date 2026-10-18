
package com.example.fleet_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/vehicles")
    public String showVehicles(Model model) {
        model.addAttribute("vehicles", vehicleRepository.findAll());
        model.addAttribute("vehicle", new Vehicle());
        return "vehicles";
    }

    @PostMapping("/vehicles/add")
    public String addVehicle(
            @ModelAttribute Vehicle vehicle,
            RedirectAttributes redirectAttributes) {

        vehicleRepository.save(vehicle);
        redirectAttributes.addFlashAttribute(
                "message", "Vehicle added successfully!");

        return "redirect:/vehicles";
    }

    @PostMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
        return "redirect:/vehicles";
    }
}
