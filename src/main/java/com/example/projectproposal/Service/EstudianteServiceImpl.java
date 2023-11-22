package com.example.projectproposal.Service;

import com.example.projectproposal.Entity.Estudiante;
import com.example.projectproposal.Repository.Dao.IEstudianteDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */
@Service
public class EstudianteServiceImpl implements IEstudianteService {

    @Autowired
    private IEstudianteDao estudianteDao;

    @Override
    public List<Estudiante> findAll() {
        return (List<Estudiante>) estudianteDao.findAll();
    }

    @Override
    public Estudiante findById(Long id) {
        return estudianteDao.findById(id).orElse(null);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteDao.save(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(Estudiante estudiante, Long id) {

        Estudiante estudianteActual = findById(id); // se realiza la consulta para encontrar la facultad
        // que esta en la base de datos

        if (null == estudianteActual) {

            return null;
        }

        // se agrega los nuevos datos ala facultad que se trajo de la base de datos
        estudianteActual.setNombres(estudiante.getNombres());
        estudianteActual.setApellidos(estudiante.getApellidos());
        estudianteActual.setCorreo(estudiante.getCorreo());
        estudianteActual.setTipoIdentificacion(estudiante.getTipoIdentificacion());
        estudianteActual.setIdentificacion(estudiante.getIdentificacion());
        estudianteActual.setCelular(estudiante.getCelular());
        estudianteActual.setNumeroCreditosAprobados(estudiante.getNumeroCreditosAprobados());
        estudianteActual.setPorcentajeCreditosAprobados(estudiante.getPorcentajeCreditosAprobados());        
        // metodo para guardar en la base datos
        return save(estudianteActual);
    }

    @Override
    public void delete(Long id) {
        estudianteDao.deleteById(id);
    }

    @Override
    public List<Estudiante> findByCorreo(String correo) {
        return (List<Estudiante>) estudianteDao.findByCorreo(correo);
    }
}
