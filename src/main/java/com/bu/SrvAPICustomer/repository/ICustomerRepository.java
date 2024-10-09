package com.bu.SrvAPICustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bu.SrvAPICustomer.model.entity.Customer;

import java.util.Optional;

public interface ICustomerRepository  extends JpaRepository<Customer, String>{

    Optional<Customer> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);

}
