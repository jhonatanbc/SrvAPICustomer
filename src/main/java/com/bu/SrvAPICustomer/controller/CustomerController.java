package com.bu.SrvAPICustomer.controller;


import javax.validation.Valid;

import com.bu.SrvAPICustomer.model.DTO.CustomerResponseDTO;
import com.bu.SrvAPICustomer.util.PathVariables;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bu.SrvAPICustomer.model.entity.Customer;
import com.bu.SrvAPICustomer.service.ICustomerService;

import java.util.Map;

@RestController
public class CustomerController {

	@Autowired
	ICustomerService iCustomerService;
	ObjectMapper objectMapper;

	@GetMapping("/consultarCliente/{identifier}")
	public ResponseEntity<?> findCustomer(@PathVariable("identifier") String identifier){
		Map<String, String> customerInfo = PathVariables.splitVariablesPath(identifier);

		String tipoDocumento = customerInfo.get("tipoDocumento");
		String numeroDocumento = customerInfo.get("numeroDocumento");
		Customer customer = iCustomerService.findByTipoDocumentoAndNumeroDocumento(tipoDocumento,numeroDocumento);
		if (customer != null)
			return ResponseEntity.ok(customer);
		else{
			String mensaje = "{Cliente "+tipoDocumento+ " "+numeroDocumento+" No se encuentra registrado.}";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
		}
	}
	
	@PostMapping("/guardarCliente")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody Customer customer) {
		if(iCustomerService.findByTipoDocumentoAndNumeroDocumento(customer.getTipoDocumento(),customer.getNumeroDocumento()) != null)
		{
			System.out.println("Existe en base de datos");
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

	@PostMapping("/actualizarCliente")
	public ResponseEntity<CustomerResponseDTO> updateCustomer(@Valid @RequestBody Customer customer) {
		if(iCustomerService.findByTipoDocumentoAndNumeroDocumento(customer.getTipoDocumento(),customer.getNumeroDocumento()) == null)
		{
			String mensaje = iCustomerService.NotFoundCustomerMessage(customer);
			CustomerResponseDTO response = new CustomerResponseDTO(customer.getIdTx(), mensaje);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		}else{
			Customer newCustomer = iCustomerService.updateCustomer(customer);
			String mensaje = iCustomerService.getSuccessUpdateMessage(newCustomer);
			CustomerResponseDTO response = new CustomerResponseDTO(customer.getIdTx(), mensaje);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

}
