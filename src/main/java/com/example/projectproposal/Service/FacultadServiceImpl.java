package com.example.projectproposal.Service;

import java.util.List;

import com.example.projectproposal.Entity.Facultad;
import com.example.projectproposal.Repository.Dao.IFacultadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultadServiceImpl implements IFacultadService {

  @Autowired
  private IFacultadDao facultadDao;

  @Override
  public List<Facultad> findAll() {

    return (List<Facultad>) facultadDao.findAll();

  }
  
  @Override
    public Facultad findFacultadWithSedes(Long id) {
        return facultadDao.findFacultadWithSedes(id);
    }

  @Override
  public Facultad findById(Long id) {
    return facultadDao.findById(id).orElse(null);
  }

  @Override
  public Facultad save(Facultad facultad) {
    return facultadDao.save(facultad);
  }

  @Override
  public Facultad updateFacultad(Facultad facultad, Long id) {
    Facultad facultadActual = findById(id); // se realiza la consulta para encontrar la facultad
                                                                       // que esta en la base de datos

        if (null == facultadActual) {
     
            return null;
        }

         // se agrega los nuevos datos ala facultad que se trajo de la base de datos
         facultadActual.setNombreFacultad(facultad.getNombreFacultad());
         facultadActual.setDescripcion(facultad.getDescripcion());
         facultadActual.setDireccion(facultad.getDireccion());
         facultadActual.setDecano(facultad.getDecano());
         facultadActual.setSitioWeb(facultad.getSitioWeb());
         facultadActual.setTelefono(facultad.getTelefono());
         facultadActual.setEmail(facultad.getEmail());
         facultadActual.setNumeroDepartamentos(facultad.getNumeroDepartamentos());
         facultadActual.setNumeroProgramasEstudio(facultad.getNumeroProgramasEstudio());
         // metodo para guardar en la base datos
    return save(facultadActual);
  }

  @Override
  public void delete(Long id) {
    facultadDao.deleteById(id);
  }

  @Override
  public List<Facultad> findByNombreFacultades(String name) {
    return (List<Facultad>) facultadDao.findByNombreFacultad(name);

  }

}
