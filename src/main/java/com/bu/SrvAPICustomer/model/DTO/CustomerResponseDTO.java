package com.bu.SrvAPICustomer.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDTO {
    private String idTx;
    private String mensaje;

    public CustomerResponseDTO(String idTx, String mensaje) {
        this.idTx = idTx;
        this.mensaje = mensaje;
    }
}

