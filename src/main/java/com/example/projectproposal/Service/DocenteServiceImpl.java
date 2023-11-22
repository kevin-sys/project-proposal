package com.example.projectproposal.Service;

import com.example.projectproposal.Entity.Docente;
import com.example.projectproposal.Repository.Dao.IDocenteDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocenteServiceImpl implements IDocenteService {

    @Autowired
    private IDocenteDao docenteDao;

    @Override
    public List<Docente> findAll() {
        return (List<Docente>) docenteDao.findAll();
    }

    @Override
    public Docente findById(Long id) {
        return docenteDao.findById(id).orElse(null);
    }

    @Override
    public Docente save(Docente docente) {
        return docenteDao.save(docente);
    }

    @Override
    public Docente updateDocente(Docente docente, Long id) {

        Docente docenteActual = findById(id);

        if (null == docenteActual) {
            return null;
        }

        docenteActual.setNombres(docente.getNombres());
        docenteActual.setApellidos(docente.getApellidos());
        docenteActual.setCorreo(docente.getCorreo());
        docenteActual.setTipoIdentificacion(docente.getTipoIdentificacion());
        docenteActual.setIdentificacion(docente.getIdentificacion());
        docenteActual.setCelular(docente.getCelular());
        return save(docenteActual);
    }

    @Override
    public void delete(Long id) {
        docenteDao.deleteById(id);
    }

    @Override
    public List<Docente> findByCorreo(String correo) {
        return (List<Docente>) docenteDao.findByCorreo(correo);
    }

}
