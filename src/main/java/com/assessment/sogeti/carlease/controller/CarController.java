package com.assessment.sogeti.carlease.controller;

import com.assessment.sogeti.carlease.data.Car;
import com.assessment.sogeti.carlease.service.CarService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/cars") // Add this to map all requests starting with /customers
public class CarController {
	
    private final CarService carService;
    
    // Initialize the final field in the constructor
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "cars/cars";
    }

    @GetMapping("/add")
    public String showAddCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "cars/add-car";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);
        return "cars/update-car";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }
    
    @PostMapping("/update")
    public String updateCar(@ModelAttribute Car car) {
    	carService.updateCar(car.getId(), car);
        return "redirect:/cars";
    }
    
    @RequestMapping("/delete/{id}")
    public RedirectView deleteCar(@PathVariable int id) {
    	carService.removeCar(id);
        return new RedirectView("/myapp/cars");
    }   
    
}
