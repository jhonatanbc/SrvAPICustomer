package com.bu.SrvAPICustomer.controller;


import javax.validation.Valid;

import com.bu.SrvAPICustomer.model.DTO.CustomerResponseDTO;
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
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody Customer customer) {
		if(iCustomerService.findCustomerById(customer.getNumeroDocumento()) != null)
		{
			String mensaje = iCustomerService.getDuplicatedCustomer(customer);
			CustomerResponseDTO response = new CustomerResponseDTO(customer.getIdTx(), mensaje);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		}else{
			Customer newCustomer = iCustomerService.save(customer);
			String mensaje = iCustomerService.getSuccessMessage(newCustomer);
			CustomerResponseDTO response = new CustomerResponseDTO(customer.getIdTx(), mensaje);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
    }

}
