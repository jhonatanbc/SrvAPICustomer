package com.bu.SrvAPICustomer.service;

import com.bu.SrvAPICustomer.model.entity.Customer;

public interface ICustomerService {
	public Customer save(Customer customer);
	public String getSuccessMessage(Customer customer);
	public Customer findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
	public String getDuplicatedCustomer(Customer customer);
	public String getSuccessUpdateMessage(Customer customer);
	public String NotFoundCustomerMessage(Customer customer);
	public Customer updateCustomer(Customer customer);

}
