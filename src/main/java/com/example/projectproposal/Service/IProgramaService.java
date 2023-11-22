package com.example.projectproposal.Service;

import java.util.List;

import com.example.projectproposal.Entity.Programa;

public interface IProgramaService {

    //metodo para consultar todas las sedes
    public List<Programa> findAll();
    //metodo para consultar por Id
    public Programa findById(Long id);
    //metodo para guardar 
    public Programa save(Programa sede);
    //metodo para actualizar
    public Programa updatePrograma(Programa sede , Long id);
    //metodo para borrar
    public void delete(Long id);
    //
    public List<Programa> findByNombreProgramas(String name);



}
