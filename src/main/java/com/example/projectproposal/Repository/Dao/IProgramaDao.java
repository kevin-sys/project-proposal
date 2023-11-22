package com.example.projectproposal.Repository.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.projectproposal.Entity.Programa;

@Repository
public interface IProgramaDao extends JpaRepository<Programa,Long>{
    
    @Query( nativeQuery = true  ,  value= "SELECT * FROM programas_estudio s WHERE s.nombre_programa like %:term% ") // Consulta a nivel de base datos
    public List<Programa> findByNombrePrograma(@Param("term") String term);
}
