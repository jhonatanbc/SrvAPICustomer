package com.bu.SrvAPICustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bu.SrvAPICustomer.model.entity.Customer;

public interface ICustomerRepository  extends JpaRepository<Customer, String>{

}
