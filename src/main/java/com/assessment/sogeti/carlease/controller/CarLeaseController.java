package com.assessment.sogeti.carlease.controller;

import com.assessment.sogeti.carlease.data.CarLease;
import com.assessment.sogeti.carlease.service.CarLeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/carleases")
public class CarLeaseController {

    private final CarLeaseService carLeaseService;

    public CarLeaseController(CarLeaseService carLeaseService) {
        this.carLeaseService = carLeaseService;
    }

    @GetMapping
    public String getAllCarLeases(Model model) {
        model.addAttribute("carLeases", carLeaseService.getAllCarLeases());
        return "carleases/carleases";
    }

    @GetMapping("/add")
    public String showAddCarLeaseForm(Model model) {
        model.addAttribute("carLease", new CarLease());
        model.addAttribute("customers", carLeaseService.getAllCustomers()); // Assume this method exists in service
        model.addAttribute("cars", carLeaseService.getAllCars()); // Assume this method exists in service
        return "carleases/add-carlease";
    }

    @PostMapping("/add")
    public String addCarLease(@ModelAttribute CarLease carLease) {
        carLeaseService.addCarLease(carLease);
        return "redirect:/carleases";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateCarLeaseForm(@PathVariable int id, Model model) {
        CarLease carLease = carLeaseService.getCarLeaseById(id);
        model.addAttribute("carLease", carLease);
        model.addAttribute("customers", carLeaseService.getAllCustomers()); // Assume this method exists in service
        model.addAttribute("cars", carLeaseService.getAllCars()); // Assume this method exists in service
        return "carleases/update-carlease";
    }

    @PostMapping("/update")
    public String updateCarLease(@ModelAttribute CarLease carLease) {
        carLeaseService.updateCarLease(carLease.getId(), carLease);
        return "redirect:/carleases";
    }

    @RequestMapping("/delete/{id}")
    public RedirectView deleteCarLease(@PathVariable int id) {
        carLeaseService.removeCarLease(id);
        return new RedirectView("/myapp/carleases");
    }
}
