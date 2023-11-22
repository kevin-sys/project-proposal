package com.example.projectproposal.Repository.Dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.Repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import  com.example.projectproposal.Entity.Sede;

@Repository
public interface ISedeDao extends JpaRepository<Sede,Long>{
   // una consulta personali
    @Query( nativeQuery = true  ,  value= "SELECT * FROM Sedes s WHERE s.nombre_Sede like %:term% ") // select a nivel de entity y no a nivel de tabla , se debe utilizar (Select * From Sede) el nombre de la entidad para realizar la consulta
    public List<Sede> findByNombreSede(@Param("term") String term);
    
}
