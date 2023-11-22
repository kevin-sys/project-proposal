package com.example.projectproposal.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;

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
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sedes")
@Data
public class Sede implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 4, max = 20)
    @Column(name = "nombre_sede", nullable = false, length = 20)
    private String nombreSede;

    @NotEmpty(message = "No puede estar vacío")
    @Column(name = "direccion", nullable = false, length = 20)
    private String direccion;

    @NotEmpty(message = "No puede estar vacío")
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Email(message = "El formato del correo no es correcto")
    @NotEmpty(message = "No puede estar vacío")
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    // Relación muchos a muchos con Facultad
    @ManyToMany(mappedBy = "sedes", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Facultad> facultades = new ArrayList<>();
}
