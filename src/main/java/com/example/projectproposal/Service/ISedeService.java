package com.example.projectproposal.Service;

import com.example.projectproposal.Entity.Sede;

import java.util.List;

public interface ISedeService {
    
    //metodo para consultar todas las sedes
    public List<Sede> findAll();
    //metodo para consultar por Id
    public Sede findById(Long id);
    //metodo para guardar 
    public Sede save(Sede sede);
    //metodo para actualizar
    public Sede updaSede(Sede sede , Long id);
    //metodo para borrar
    public void delete(Long id);
    //
    public List<Sede> findByNombreSedes(String name);


}
