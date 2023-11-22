package com.example.projectproposal.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Table(name= "propuesta")
@Entity // anotacion entity para declarar  una clase como entidad o objeto
public class Propuesta implements Serializable {

    private static final long serialVersionUID = 1L;
     
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_propuesta")
    private Long id; 

    @Column(name="codigo_propuesta")
    private String codidoPropuesta ;
    
    @NotEmpty(message= "No puede estar vacio") // 'message' mesanje custom para erorres 
    @Size(min = 4 ,  max = 20)
    @Column(name= "titulo_propuesta" ,nullable = false , length = 20)
    private String tituloPropuesta ;
    
    @NotEmpty(message= "No puede estar vacio") // 'message' mesanje custom para erorres 
    @Size(min = 4 ,  max = 20)
    @Column(name= "info_general" ,nullable = false , length = 20)
    private String info_General ;
    
    @NotEmpty(message= "No puede estar vacio") // 'message' mesanje custom para erorres 
    @Size(min = 4 ,  max = 20)
    @Column(name= "info_especifica" ,nullable = false , length = 20)
    private String  infoEspecifica;
    
    @NotEmpty(message= "No puede estar vacio") // 'message' mesanje custom para erorres 
    @Size(min = 4 ,  max = 20)
    @Column(name= "plantamiento" ,nullable = false , length = 20)
    private String plantemiento ;
    
    @NotEmpty(message= "No puede estar vacio") // 'message' mesanje custom para erorres 
    @Size(min = 4 ,  max = 20)
    @Column(name= "justificacion" ,nullable = false , length = 20)
    private String justificacion;

    @NotEmpty(message= "No puede estar vacio") // 'message' mesanje custom para erorres 
    @Size(min = 4 ,  max = 20)
    @Column(name= "objetivo_general" ,nullable = false , length = 20)
    private String objetivoGeneral ;
    
     // 'message' mesanje custom para erorres 
    
    // @OneToMany(cascade = CascadeType.ALL)
    // @Column(name= "biblografia" ,nullable = false)
    // private List<Bibliografia> biblografia;
    
        
   
    @Column(name= "biblografia" ,nullable = false)
    private Bibliografia biblografia;

    @Column(name= "idEstudiante1" ,nullable = false)
    private String idEstudiante1;
    
    @Column(name= "idEstudiante2" ,nullable = false)
    private String idEstudiante2;
    
    @Column(name= "idEstudiante3" ,nullable = false)
    private String idEstudiante3;

    @NotNull(message = "No puede estar vacío")
    @Column(name = "estado" ,columnDefinition = "VARCHAR(30) DEFAULT 'sin estado'")
    private String  estado;

    @Column(name= "id_nexo")
    private String idNexo;
    
      

 
    @Column(name="create_at")//nombre de la columna en la base de datos
	@Temporal(TemporalType.DATE)// el formato de fecha 
	private Date createAt;
  
    @PrePersist // anotacion para ejecutar una funcion antes de guardad en la base dato o antes que se ejecute la consulta en la base de datos
    public void prePersist(){
        createAt = new Date();
            }
}