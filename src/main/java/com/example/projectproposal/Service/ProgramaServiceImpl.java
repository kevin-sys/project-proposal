package com.example.projectproposal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectproposal.Entity.Facultad;
import com.example.projectproposal.Entity.Programa;
import com.example.projectproposal.Entity.Sede;
import com.example.projectproposal.Repository.Dao.IProgramaDao;

import jakarta.transaction.Transactional;

@Service
public class ProgramaServiceImpl implements IProgramaService{
    

   @Autowired
   private IProgramaDao programaDao;


    @Override
    public List<Programa> findAll() {
       return (List<Programa>) programaDao.findAll();
    }

    @Override
    public Programa findById(Long id) {
        return programaDao.findById(id).orElse(null);
    }

    @Override
    public Programa save(Programa programa) {
       return programaDao.save(programa);
    }

    @Override
    public Programa updatePrograma(Programa programa, Long id) {
     Programa programaActual = findById(id); // se realiza la consulta para encontrar la sede
                                                                       // que esta en la base de datos

        if (null == programaActual) {
     
            return null;
        }

         // se agrega los nuevos datos ala sede que se trajo de la base de datos
         programaActual.setNombrePrograma(programa.getNombrePrograma());
         programaActual.setFacultad(programa.getFacultad());
         programaActual.setDuracion(programa.getDuracion());
         programaActual.setRequisitos(programa.getRequisitos());
         programaActual.setDescripcion(programa.getDescripcion());
         programaActual.setCreditos(programa.getCreditos());
         programaActual.setDirector(programa.getDirector());
         programaActual.setCupoMaximo(programa.getCupoMaximo());
         programaActual.setModalidad(programa.getModalidad());
        programaActual.setActivo(programa.getActivo());
        programaActual.setNivel(programa.getNivel());
        programaActual.setPlanEstudios(programa.getPlanEstudios());
        programaActual.setObjetivo(programa.getObjetivo());
         
         // metodo para guardar en la base datos

         programaDao.save(programaActual);
         return programaActual;
    }

    @Override
    @Transactional
    public void delete(Long id) {
         programaDao.deleteById(id);
    }

    @Override
      @Transactional
    public List<Programa> findByNombreProgramas(String name) {
        
       return programaDao.findByNombrePrograma(name);
    }


    
}
