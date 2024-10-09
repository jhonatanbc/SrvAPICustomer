package com.bu.SrvAPICustomer.service;

import com.bu.SrvAPICustomer.model.entity.Customer;

public interface ICustomerService {
	public Customer save(Customer customer);

	public Customer findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);

	public Customer updateCustomer(Customer customer);

}
