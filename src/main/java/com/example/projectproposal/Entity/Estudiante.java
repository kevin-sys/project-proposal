/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projectproposal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "estudiantes")
@Entity
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Digite el nombre")
    @Size(min = 3, max = 25)
    @Column(name = "nombres", nullable = false, length = 25)
    private String nombres;

    @NotEmpty(message = "Digite los apellidos")
    @Column(name = "apellidos", length = 30, nullable = false)
    private String apellidos;

    @Email(message = "El formato del correo no es correcto")
    @NotEmpty(message = "No puede estar vacio")
    @Column(name = "correo", length = 50, unique = true)
    private String correo;

    @NotEmpty(message = "Digite el tipo de identificación")
    @Column(length = 35, nullable = false)
    private String tipoIdentificacion;

    @NotEmpty(message = "Digite la identificación")
    @Column(length = 20, nullable = false)
    private String identificacion;

    //@Digits(integer = 3, fraction = 0, message = "El número de creditos aprobados debe tener máximo 3 dígitos")
    //@Max(value = 999, message = "El número de creditos debe ser menor o igual a 999")
    @Column(name = "num_creditos_apro", length = 3)
    private Integer numeroCreditosAprobados;
    
    @Column(name = "porcentaje_creditos_apro", length = 3)
    private Integer porcentajeCreditosAprobados;

    @NotEmpty(message = "Digite el celular")
    @Column(name = "celular", length = 10)
    private String celular;
    
    @Column(name = "id_facultad", length = 3)
    private Long idFacultad;
    
    @Column(name = "id_programa", length = 3)
    private Long idPrograma;
    
    @Column(name = "id_sede", length = 3)
    private Long idSede;

    @Column(name = "fecha_registro")//nombre de la columna en la base de datos
    @Temporal(TemporalType.DATE)// el formato de fecha 
    private Date fechaRegistro;

    @PrePersist // anotacion para ejecutar una funcion antes de guardad en la base dato o antes que se ejecute la consulta en la base de datos
    public void prePersist() {
        fechaRegistro = new Date();
    }
}
