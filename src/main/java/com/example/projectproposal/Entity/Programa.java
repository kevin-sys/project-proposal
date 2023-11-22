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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import lombok.NoArgsConstructor;
import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "programas_estudio")
@Data
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4, max = 20)
    @Column(name = "codigo_programa", nullable = false, length = 20) 
    private String codigo ;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4)
    @Column(name = "nombre_programa", nullable = false, length = 50)
    private String nombrePrograma;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4)
    @Column(nullable = false, length = 50)
    private String facultad;

    @NotNull(message = "No puede estar vacío")
    @Column(length = 10)
    private Integer duracion;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4)
    @Column(nullable = false, length = 150)
    private String requisitos;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4)
    @Column(nullable = false, length = 150)
    private String descripcion;

    @NotNull(message = "No puede estar vacío")
    @Column(length = 10)
    private Integer creditos;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4, max = 20)
    @Column(name = "nombre_director", nullable = false, length = 50)
    private String director;

    @NotNull(message = "No puede estar vacío")
    @Column(name = "cupo_maximo", length = 10)
    private Integer cupoMaximo;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4, max = 20)
    @Column(name = "modalidad", nullable = false, length = 50)
    private String modalidad;

    @NotNull(message = "No puede estar vacío")
    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4, max = 20)
    @Column(name = "nivel", nullable = false, length = 50)
    private String nivel;

    @NotNull(message = "No puede estar vacío")
    @Column(name = "costo_matricula", nullable = false)
    private Double costoMatricula;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4)
    @Column(name = "plan_estudios", nullable = false, length = 50)
    private String planEstudios;

    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4)
    @Column(name = "objetivo_programa", nullable = false, length = 150)
    private String objetivo;
    
    @NotEmpty(message = "No puede estar vacio") // 'message' mesanje custom para erorres
    @Size(min = 4)
    @Column(name = "registro_sinies", nullable = false, length = 20)
    private String registroSinies ;

    @Column(name = "create_at") // nombre de la columna en la base de datos
    @Temporal(TemporalType.DATE) // el formato de fecha
    private Date createAt;

    @PrePersist // anotacion para ejecutar una funcion antes de guardad en la base dato o antes
                // que se ejecute la consulta en la base de datos
    public void prePersist() {
        createAt = new Date();
    }

}
