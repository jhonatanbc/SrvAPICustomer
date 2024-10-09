package com.bu.SrvAPICustomer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bu.SrvAPICustomer.model.entity.Customer;
import com.bu.SrvAPICustomer.repository.ICustomerRepository;
import com.bu.SrvAPICustomer.service.ICustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	ICustomerRepository iCustomerRepository;
	@Override
	public Customer save(Customer customer) {
		return iCustomerRepository.save(customer);
	}

	@Override
	public String getSuccessMessage(Customer customer) {
		return String.format("Cliente %s almacenado de forma exitosa.", customer.getNumeroDocumento());
	}

	@Override
	public Customer findCustomerById(String numeroDocumento) {
		Optional<Customer> customerOptional = iCustomerRepository.findById(numeroDocumento);
		return customerOptional.orElse(null) ;
	}
	@Override
	public String getDuplicatedCustomer(Customer customer) {
		return String.format("Cliente %s %s. Ya se encuentra registrado.", customer.getTipoDocumento(), customer.getNumeroDocumento());
	}

}
