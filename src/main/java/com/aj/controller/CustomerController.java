package com.aj.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aj.entity.Customer;
import com.aj.repo.CustomerRepository;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository repo;

    @GetMapping("/")
    public String loadForm(Model model) {
        model.addAttribute("cust", new Customer());
        return "index";
    }


    @GetMapping("/details")
    public String getAllCustomers(Model model) {
        List<Customer> listOfCust = repo.findAll();
        if (listOfCust != null) {
            model.addAttribute("customers", listOfCust);
        }
        return "data";
    }
    
    @GetMapping("delete")
    public String removeCustomer(@RequestParam("id") Integer id, Model model) {
    	repo.deleteById(id);
    	model.addAttribute("msg","Deleted Successflly");
    	model.addAttribute("customers",repo.findAll());
    	return "data";
    }
    
    @PostMapping("/customer")
    public String handleSubmit(@Validated @ModelAttribute("cust") Customer c, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        
        if (c.getId() == null) {
            // Adding a new customer
            Customer savedCustomer = repo.save(c);
            if (savedCustomer != null && savedCustomer.getId() != null) {
                model.addAttribute("message", "Customer added successfully!");
            } else {
                model.addAttribute("errorMessage", "Failed to add customer!");
            }
        } else {
            // Updating an existing customer
            Customer updateCustomer = repo.save(c);
            if (updateCustomer != null && updateCustomer.getId() != null) {
                model.addAttribute("message", "Customer updated successfully!");
                return "data";
            } else {
                model.addAttribute("errorMessage", "Failed to update customer!");
            }
        }
        
        return "index";
    }


    @GetMapping("/edit")
    public String editCustomer(@RequestParam("id") Integer id, Model model) {
        Optional<Customer> findById = repo.findById(id);
        if (findById.isPresent()) {
            Customer customer = findById.get();
            model.addAttribute("cust", customer); // Use "cust" attribute to populate the form fields
            return "index"; // Return to the form with populated fields for editing
        } else {
            return "redirect:/details?error=Customer+not+found";
        }
    }

}
