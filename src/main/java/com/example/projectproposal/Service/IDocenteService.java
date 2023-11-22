/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.projectproposal.Service;

import com.example.projectproposal.Entity.Docente;
import java.util.List;

/**
 *
 * @author pc
 */
public interface IDocenteService {

    public List<Docente> findAll();

    public Docente findById(Long id);

    public Docente save(Docente docente);

    public Docente updateDocente(Docente docente, Long id);

    public void delete(Long id);

    public List<Docente> findByCorreo(String correo);
}
