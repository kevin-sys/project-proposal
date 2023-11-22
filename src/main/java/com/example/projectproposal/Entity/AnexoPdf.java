package com.example.projectproposal.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AnexoPdf {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre_archivo", nullable = false)
    private String nombreArchivo;

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name = "archivo_pdf", columnDefinition = "MEDIUMBLOB",length = 10000000 )
    private byte[] archivoPDF;

    // @ManyToOne
    // @JoinColumn(name = "id_propuesta")
    // private Propuesta propuesta;
}
