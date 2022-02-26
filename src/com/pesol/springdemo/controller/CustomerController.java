package com.pesol.springdemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pesol.springdemo.entity.Customer;
import com.pesol.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// need to inject the customer service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required = false, name = "sort") String sort) {
		
		// get customers from customer service
		List<Customer> customers = null;
		
		// check for sort field
		if (sort != null) {
			int theSort = Integer.parseInt(sort);
			customers = customerService.getCustomers(theSort);
		} else {
			customers = customerService.getCustomers();
		}
		
		// add the customers to the model
		theModel.addAttribute("customers", customers);

		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer newCustomer = new Customer();
		
		theModel.addAttribute("customer", newCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute(name = "customer") Customer newCustomer) {
		
		// save the customer using customer service
		customerService.saveCustomer(newCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {
		
		// get the customer from database
		Customer customer = customerService.getCustomer(theId);
		
		// set the customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", customer);
		
		// send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	// mapping for search by firstName or lastName
	@GetMapping("/search")
	public String search(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		
		// search customers from service
		List<Customer> customers = customerService.searchCustomer(theSearchName);
		
		// add customers to the model
		theModel.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// for test....
	@RequestMapping("/test")
	public String test(HttpServletRequest req, Model theModel) {

		String test = req.getParameter("test");
		theModel.addAttribute("key", test);

		return "list-customers";
	}

	// for test....
	@RequestMapping("/test2")
	public String test2(HttpServletRequest req, HttpServletResponse res, Model theModel) {

		theModel.addAttribute("key", req.getParameter("test"));
		theModel.addAttribute("state", res.getStatus());

		return "list-customers";
	}

	// for test....
	@RequestMapping("/test3")
	public String test3(@RequestParam(value = "test", defaultValue = "thisIsDefaultValue") String test,
			HttpServletResponse res, Model theModel) {

		theModel.addAttribute("key", test);
		theModel.addAttribute("state", res.getStatus());

		return "list-customers";
	}
}
