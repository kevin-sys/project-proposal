package com.example.projectproposal.Repository.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.projectproposal.Entity.Facultad;

@Repository
public interface IFacultadDao extends JpaRepository<Facultad, Long> {
// una consulta personali

    @Query(nativeQuery = true, value = "SELECT * FROM Facultades s WHERE s.nombrefacultad like %:term% ") // Consulta a nivel de base datos
    public List<Facultad> findByNombreFacultad(@Param("term") String term);

    // Consulta personalizada para obtener una Facultad por su ID junto con las Sedes asociadas
    @Query("SELECT f FROM Facultad f LEFT JOIN FETCH f.sedes WHERE f.id = :id")
    public Facultad findFacultadWithSedes(@Param("id") Long id);

}
