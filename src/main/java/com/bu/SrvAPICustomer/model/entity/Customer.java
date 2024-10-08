package com.bu.SrvAPICustomer.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	
	@NotBlank(message = "El campo 'idTx' es obligatorio")
    private String idTx;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;
    
    @Id
    @Column(name = "numeroDocumento", unique = true, nullable = false)
    @NotNull(message = "El número de documento es obligatorio")
    private String numeroDocumento;

    @NotBlank(message = "El primer nombre es obligatorio")
    private String primerNombre;

    private String segundoNombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    private String primerApellido;

    private String segundoApellido;

    @NotNull(message = "El teléfono es obligatorio")
    private Integer telefono;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    private String correoElectronico;

}
