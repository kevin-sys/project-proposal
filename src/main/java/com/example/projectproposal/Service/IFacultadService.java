package com.example.projectproposal.Service;

import java.util.List;

import com.example.projectproposal.Entity.Facultad;





public interface IFacultadService {

    //metodo para consultar todas las sedes
    public List<Facultad> findAll();
    //metodo para consultar por Id
    public Facultad findById(Long id);
    //metodo para guardar 
    public Facultad save(Facultad sede);
    //metodo para actualizar
    public Facultad updateFacultad(Facultad sede , Long id);
    //metodo para borrar
    public void delete(Long id);
    //
    public List<Facultad> findByNombreFacultades(String name);
    
    Facultad findFacultadWithSedes(Long id);
    


}
