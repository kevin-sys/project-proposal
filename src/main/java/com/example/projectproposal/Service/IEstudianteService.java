/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.projectproposal.Service;

import com.example.projectproposal.Entity.Estudiante;
import java.util.List;

/**
 *
 * @author pc
 */
public interface IEstudianteService {

    //metodo para consultar todos los estudiantes
    public List<Estudiante> findAll();

    //metodo para consultar por Id
    public Estudiante findById(Long id);

    //metodo para guardar 
    public Estudiante save(Estudiante estudiante);

    //metodo para actualizar
    public Estudiante updateEstudiante(Estudiante estudiante, Long id);

    //metodo para borrar
    public void delete(Long id);

    //Buscar por correo
    public List<Estudiante> findByCorreo(String correo);
}
