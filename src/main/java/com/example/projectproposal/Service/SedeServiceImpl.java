package com.example.projectproposal.Service;

import com.example.projectproposal.Entity.Sede;
import com.example.projectproposal.Repository.Dao.ISedeDao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeServiceImpl implements ISedeService {

    @Autowired // injecion de dependecia para utulizar la calse que extiende de crudrepository
               // jpa
    private ISedeDao SedeDao;

    @Override
    @Transactional(readOnly = true) // está anotado con @Transactional(readOnly = true), lo que indica que la
                                   // transacción es de solo lectura.
    public List<Sede> findAll() {
        return (List<Sede>) SedeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Sede findById(Long id) {

        return SedeDao.findById(id).orElse(null);

    }

    @Override
    @Transactional
    public Sede save(Sede sede) {
        return SedeDao.save(sede);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        SedeDao.deleteById(id);
    }

    @Override
    @Transactional
    public Sede updaSede(Sede sede , Long id) {

        Sede sedeActual = findById(id); // se realiza la consulta para encontrar la sede
                                                                       // que esta en la base de datos

        if (null == sedeActual) {
     
            return null;
        }

         // se agrega los nuevos datos ala sede que se trajo de la base de datos
         sedeActual.setNombreSede(sede.getNombreSede());
         sedeActual.setDireccion(sede.getDireccion());
         sedeActual.setTelefono(sede.getTelefono());
         sedeActual.setEmail(sede.getEmail());
         // metodo para guardar en la base datos

         SedeDao.save(sedeActual);
         return sedeActual;
        
       

    }

    @Override
    @Transactional(readOnly = true)
    public List<Sede> findByNombreSedes(String name) {
        return SedeDao.findByNombreSede(name);

    }


    

}
