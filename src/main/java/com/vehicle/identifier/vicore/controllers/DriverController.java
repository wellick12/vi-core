package com.vehicle.identifier.vicore.controllers;

import com.vehicle.identifier.vicore.models.Driver;
import com.vehicle.identifier.vicore.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Controller
public class DriverController {

	@Autowired
	DriverService driverService;


	@GetMapping("/drivers")
	public String getDrivers(Model model) {
		model.addAttribute("addDriver", new Driver());
		model.addAttribute("drivers", driverService.getDrivers());
		return "Driver";
	}	
	
	@PostMapping("/drivers/addNew")
	public String addNew(@ModelAttribute Driver driver) {
		driver.setCreated(ZonedDateTime.now());
		driver.setId(String.valueOf(UUID.randomUUID()));
		driverService.save(driver);
		return "redirect:/drivers";
	}
	
	@GetMapping("drivers/findbyid/{id}")
	public Driver findById(String id) {
	  return driverService.findById(id);
	}	
	
	@RequestMapping(value="/drivers/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Driver driver) {
		driverService.save(driver);
		return "redirect:/drivers";
	}
	
	@RequestMapping(value="/drivers/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		driverService.delete(id);
		return "redirect:/drivers";
	}
	
}
