
package com.example.fleet_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DriverController {

    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping("/drivers")
    public String showDrivers(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        model.addAttribute("driver", new Driver());
        return "drivers";
    }

    @PostMapping("/drivers/add")
    public String addDriver(
            @ModelAttribute Driver driver,
            RedirectAttributes redirectAttributes) {

        driverRepository.save(driver);

        redirectAttributes.addFlashAttribute(
                "message", "Driver added successfully!");

        return "redirect:/drivers";
    }

    @PostMapping("/drivers/delete/{id}")
    public String deleteDriver(@PathVariable Long id) {
        driverRepository.deleteById(id);
        return "redirect:/drivers";
    }
}
