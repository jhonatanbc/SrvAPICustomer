package com.bu.SrvAPICustomer.model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

@Entity
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tipoDocumento", "numeroDocumento"})
})
@Data

public class Customer {
    @Id
	@NotBlank(message = "El campo 'idTx' es obligatorio")
    private String idTx;

    @Column(name = "tipoDocumento", nullable = false)
    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;
    

    @Column(name = "numeroDocumento", nullable = false)
    @NotBlank(message = "El número de documento es obligatorio")
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
    @Email(message = "Campo 'correElectronico', no cumple con la estructura de un correo electrónico valido.")
    private String correoElectronico;

}
