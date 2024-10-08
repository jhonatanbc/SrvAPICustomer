package com.bu.SrvAPICustomer.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bu.SrvAPICustomer.model.entity.Customer;
import com.bu.SrvAPICustomer.service.ICustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	ICustomerService iCustomerService;
	
	@GetMapping("/status")
	public String status() {
		return "OK";
	}
	
	@PostMapping("/guardarCliente")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer newCustomer = iCustomerService.save(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

}
