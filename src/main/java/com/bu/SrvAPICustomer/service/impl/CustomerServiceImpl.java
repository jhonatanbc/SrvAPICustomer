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
	public Customer findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento) {
		Optional<Customer> customerOptional = iCustomerRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento,numeroDocumento);
		return customerOptional.orElse(null) ;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer customerFound = findByTipoDocumentoAndNumeroDocumento(customer.getTipoDocumento(),customer.getNumeroDocumento());
		customer.setIdTx(customerFound.getIdTx());
		return iCustomerRepository.save(customer);
	}
}
