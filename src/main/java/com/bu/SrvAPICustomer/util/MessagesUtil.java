package com.bu.SrvAPICustomer.util;

import com.bu.SrvAPICustomer.model.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class MessagesUtil {
    public String getSuccessMessage(Customer customer) {
        return String.format("Cliente %s almacenado de forma exitosa.", customer.getNumeroDocumento());
    }

    public String getDuplicatedCustomer(Customer customer) {
        return String.format("Cliente %s %s. Ya se encuentra registrado.", customer.getTipoDocumento(), customer.getNumeroDocumento());
    }

    public String getSuccessUpdateMessage(Customer customer) {
        return String.format("Cliente %s actualizado de forma exitosa.", customer.getNumeroDocumento());
    }

    public String NotFoundCustomerMessage(Customer customer) {
        return String.format("Cliente %s %s. No se encuentra registrado.", customer.getTipoDocumento(), customer.getNumeroDocumento());
    }
}
