package com.example.projectproposal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import java.util.List;

import jakarta.persistence.JoinTable;


import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facultades")
@Data
public class Facultad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 4, max = 20)
    @Column(name = "nombrefacultad", nullable = false, length = 20)
    private String nombreFacultad;

    @NotEmpty(message = "No puede estar vacío")
    @Column(name = "description", length = 150, nullable = false)
    private String descripcion;

    @NotEmpty(message = "No puede estar vacío")
    @Column(length = 20, nullable = false)
    private String direccion;

    @NotEmpty(message = "No puede estar vacío")
    @Column(length = 20, nullable = false)
    private String telefono;

    @NotEmpty(message = "No puede estar vacío")
    @Column(length = 50, nullable = false)
    private String sitioWeb;

    @Email(message = "El formato del correo no es correcto")
    @NotEmpty(message = "No puede estar vacío")
    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "No puede estar vacío")
    @Column(length = 30, nullable = false)
    private String decano;

    @Column(name = "numero_departamentos", length = 10)
    private Integer numeroDepartamentos;

    @Column(name = "numero_programas_estudio", length = 10)
    private Integer numeroProgramasEstudio;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    // Relación muchos a muchos con Sede
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "facultad_sede",
        joinColumns = @JoinColumn(name = "facultad_id"),
        inverseJoinColumns = @JoinColumn(name = "sede_id")
    )
    @JsonIgnoreProperties("facultades")
    private List<Sede> sedes = new ArrayList<>();
}
