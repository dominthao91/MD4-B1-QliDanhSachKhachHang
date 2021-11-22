package hhh.controller;

import hhh.model.Customer;
import hhh.service.ICustomerService;
import hhh.service.CustomerServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private ICustomerService customerService;

    //    private ICustomerService ICustomerService = CustomerServiceFactory.getInstance();
    @GetMapping("/customer")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("customer/{id}")
    public ModelAndView showInfo(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("info");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);

        return modelAndView;
    }

    @PostMapping("/customer/{id}")
    public ModelAndView edit(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String address) {

        Customer customer = customerService.findOne(id);
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);

        ModelAndView modelAndView = new ModelAndView("info");

        modelAndView.addObject("customer", customer);

        return modelAndView;
    }
}
