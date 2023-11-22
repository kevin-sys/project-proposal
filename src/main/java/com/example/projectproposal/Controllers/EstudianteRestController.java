package com.example.projectproposal.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.projectproposal.Entity.Estudiante;
import com.example.projectproposal.Service.IEstudianteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/proposal")
public class EstudianteRestController {

    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping("/estudiantes")
    public List<Estudiante> index() {
        return estudianteService.findAll();
    }

    @GetMapping("/findByIdEstudiante/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") Long id) {

        Estudiante estudiante = null;
        Map<String, Object> response = new HashMap<>(); // instancia de hashmap

        try {
            estudiante = estudianteService.findById(id); // consutar el objeto en la base de datos
        } catch (DataAccessException e) {
            // TODO: handle exception
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // si no se encuentra el objecto que se busca en la base de datos
        if (estudiante == null) {
            response.put("mensaje", "El estudiante con el ID:".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);

    }

    @PostMapping("/postEstudiante")
    public ResponseEntity<?> create(@Valid @RequestBody Estudiante Estudiante, BindingResult result) {

        Estudiante EstudianteNew = null;
        Map<String, Object> response = new HashMap<>(); // instancia de hashmap para crear un map para el manejo de
        // errores

        if (result.hasErrors()) {

            // Se crea una lista de errores (errores que vienen del front en formato json)
            List<String> errors = result.getFieldErrors().stream().map(
                    fieldError -> "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }
        // manejo de errores en la consulta en la base de datos
        try {
            EstudianteNew = estudianteService.save(Estudiante);// metodo para guardar en la base datos
        } catch (DataAccessException e) {
            // TODO: handle exception
            response.put("mensaje", "Error al guardar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //
        response.put("mensaje", "El estudiante ha sido registrado con exito!");
        response.put("estudiante", EstudianteNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/findByCorreo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable("correo") String correo) {

        List<Estudiante> estudiantes;
        Map<String, Object> response = new HashMap<>(); // instancia de hashmap

        try {
            estudiantes = estudianteService.findByCorreo(correo); // consutar el objeto en la base de datos
            // si no se encuentra el objecto que se busca en la base de datos
            if (estudiantes == null) {
                response.put("mensaje", "El estudiante con ID:".concat(correo.concat(" no existe en la base de datos")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<List<Estudiante>>(estudiantes, HttpStatus.OK);

        } catch (DataAccessException e) {
            // TODO: handle exception
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateEstudiante/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Estudiante estudiante, BindingResult result) {

        Estudiante EstudianteUpdate = null;

        Map<String, Object> response = new HashMap<>(); // instancia de hashmap para crear un map para el manejo de errores
        // valida si encontro algun error de las validaciones que anotamos en la clase entity

        if (result.hasErrors()) {

            // Se crea una lista de errores (errores que vienen del front en formato json)
            List<String> errors = result.getFieldErrors().stream().map(
                    fieldError -> "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }
        try {
            EstudianteUpdate = estudianteService.updateEstudiante(estudiante, id);

            // validacion para saber si encontro el estudiante en la base de datos
            if (EstudianteUpdate == null) {

                response.put("mensaje", "El estudiante con ID:".concat(id.toString().concat(" no existe en la base de datos")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            // TODO: handle exception
            // manejo de exception de la base de datos

            response.put("mensaje", "Error al realizar la actualizacion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El estudiante ha sido actualizado con exito!");
        response.put("Estudiante", EstudianteUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        // el metodo sava de la clase crudRespository
        // se utiliza el mismo metodo que en
        // el crear para actualizar esto por
        // detra
        // se entiendo como merger , si el metodo trae un id este lo actuliza de lo
        // contraio crear el objeto en la base de datos
    }

    @DeleteMapping("/deteleEstudiante/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Estudiante EstudianteActual = estudianteService.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (EstudianteActual == null) {
            response.put("mensaje", "El estudiante con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            // metodo para eleminar un estudiante de la base de datos
            estudianteService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            response.put("mensaje", "El estudiante con el ID " + id + " no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            // TODO: handle exception
            response.put("mensaje", "Ha ocurido un error al intentar eliminar un estudiante");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El estudiante se ha eliminado con exito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
