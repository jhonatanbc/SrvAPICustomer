package com.bu.SrvAPICustomer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bu.SrvAPICustomer.model.entity.Customer;
import com.bu.SrvAPICustomer.repository.ICustomerRepository;
import com.bu.SrvAPICustomer.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	ICustomerRepository iCustomerRepository;
	@Override
	public Customer save(Customer customer) {
		return iCustomerRepository.save(customer);
	}

}
