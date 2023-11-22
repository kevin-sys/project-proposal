package com.example.projectproposal.Repository.Dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.projectproposal.Entity.Estudiante;


@Repository
public interface IEstudianteDao extends JpaRepository<Estudiante,Long> {
// una consulta personalizada
    @Query( nativeQuery = true  ,  value= "SELECT * FROM Estudiantes e WHERE e.correo like %:term% ") // Consulta a nivel de base datos
    public List<Estudiante> findByCorreo(@Param("term") String term);
}
    
