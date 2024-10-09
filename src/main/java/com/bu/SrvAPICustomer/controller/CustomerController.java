package com.bu.SrvAPICustomer.controller;


import javax.validation.Valid;

import com.bu.SrvAPICustomer.model.DTO.CustomerResponseDTO;
import com.bu.SrvAPICustomer.util.PathVariables;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bu.SrvAPICustomer.model.entity.Customer;
import com.bu.SrvAPICustomer.service.ICustomerService;

import java.util.Map;
@Slf4j
@RestController
public class CustomerController {

	@Autowired
	ICustomerService iCustomerService;
	private final ObjectMapper objectMapper;
	public CustomerController(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@GetMapping("/consultarCliente/{identifier}")
	public ResponseEntity<?> findCustomer(@PathVariable("identifier") String identifier){
		try {
			log.info("Servicio: /consultarCliente/{}", identifier);
			Map<String, String> customerInfo = PathVariables.splitVariablesPath(identifier);
			String tipoDocumento = customerInfo.get("tipoDocumento");
			String numeroDocumento = customerInfo.get("numeroDocumento");
			Customer customer = iCustomerService.findByTipoDocumentoAndNumeroDocumento(tipoDocumento,numeroDocumento);
			if (customer != null){
				log.info("Response Body: {}", objectMapper.writeValueAsString(customer));
				return ResponseEntity.ok(customer);
			}
			else{
				String mensaje = "{Cliente "+tipoDocumento+ " "+numeroDocumento+" No se encuentra registrado.}";
				log.info("Response Body: "+ mensaje);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
			}
		}catch (Exception e){
		log.error(" Error técnico en servicio /consultarCliente: {}" + e);
		return (ResponseEntity<CustomerResponseDTO>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@PostMapping("/guardarCliente")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody Customer customer) {
		try {
			log.info("idTx:"+customer.getIdTx()+" - Request Body: {}", objectMapper.writeValueAsString(customer));
			if(iCustomerService.findByTipoDocumentoAndNumeroDocumento(customer.getTipoDocumento(),customer.getNumeroDocumento()) != null)
			{
				String mensaje = iCustomerService.getDuplicatedCustomer(customer);
				CustomerResponseDTO response = new CustomerResponseDTO(customer.getIdTx(), mensaje);
				log.info("idTx:"+customer.getIdTx()+" - Response Body: {}", objectMapper.writeValueAsString(response));
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}else{
				Customer newCustomer = iCustomerService.save(customer);
				String mensaje = iCustomerService.getSuccessMessage(newCustomer);
				CustomerResponseDTO response = new CustomerResponseDTO(customer.getIdTx(), mensaje);
				log.info("idTx:"+customer.getIdTx()+" - Response Body: {}", objectMapper.writeValueAsString(response));
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		}catch (Exception e){
			log.error("idTx:"+customer.getIdTx()+" Error técnico: {}" + e);
			return (ResponseEntity<CustomerResponseDTO>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@PostMapping("/actualizarCliente")
	public ResponseEntity<CustomerResponseDTO> updateCustomer(@Valid @RequestBody Customer customer) {
		try {
			log.info("idTx:"+customer.getIdTx()+" - Request Body: {}", objectMapper.writeValueAsString(customer));
			if(iCustomerService.findByTipoDocumentoAndNumeroDocumento(customer.getTipoDocumento(),customer.getNumeroDocumento()) == null)
			{
				String mensaje = iCustomerService.NotFoundCustomerMessage(customer);
				CustomerResponseDTO response = new CustomerResponseDTO(customer.getIdTx(), mensaje);
				log.info("idTx:"+customer.getIdTx()+" - Response Body: {}", objectMapper.writeValueAsString(response));
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

			}else{
				Customer newCustomer = iCustomerService.updateCustomer(customer);
				String mensaje = iCustomerService.getSuccessUpdateMessage(newCustomer);
				CustomerResponseDTO response = new CustomerResponseDTO(customer.getIdTx(), mensaje);
				log.info("idTx:"+customer.getIdTx()+" - Response Body: {}", objectMapper.writeValueAsString(response));
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}catch (Exception e){
			log.error("idTx:"+customer.getIdTx()+" Error técnico: {}" + e);
			return (ResponseEntity<CustomerResponseDTO>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
