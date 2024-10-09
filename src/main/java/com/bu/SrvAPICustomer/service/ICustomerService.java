package com.bu.SrvAPICustomer.service;

import com.bu.SrvAPICustomer.model.entity.Customer;

public interface ICustomerService {
	public Customer save(Customer customer);
	public String getSuccessMessage(Customer customer);
	public Customer findCustomerById(String numeroDocumento);
	public String getDuplicatedCustomer(Customer customer);

}
