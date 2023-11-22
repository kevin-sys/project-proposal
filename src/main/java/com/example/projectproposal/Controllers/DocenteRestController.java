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

import com.example.projectproposal.Entity.Docente;
import com.example.projectproposal.Service.IDocenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proposal")
public class DocenteRestController {

    @Autowired
    private IDocenteService docenteService;

    @GetMapping("/docentes")
    public List<Docente> index() {
        return docenteService.findAll();
    }

    @GetMapping("/findByIdDocente/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        Docente docente = null;
        Map<String, Object> response = new HashMap<>();

        try {
            docente = docenteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (docente == null) {
            response.put("mensaje", "El docente con el ID " + id + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Docente>(docente, HttpStatus.OK);
    }

    @PostMapping("/postDocente")
    public ResponseEntity<?> create(@Valid @RequestBody Docente docente, BindingResult result) {

        Docente docenteNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            docenteNew = docenteService.save(docente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El docente ha sido registrado con éxito");
        response.put("docente", docenteNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateDocente/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Docente docente,
            BindingResult result) {

        Docente docenteUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            docenteUpdate = docenteService.updateDocente(docente, id);

            if (docenteUpdate == null) {
                response.put("mensaje", "El docente con el ID " + id + " no existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualización en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El docente ha sido actualizado con éxito");
        response.put("docente", docenteUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteDocente/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Docente docenteActual = docenteService.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (docenteActual == null) {
            response.put("mensaje", "El docente con el ID " + id + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            docenteService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            response.put("mensaje", "El docente con el ID " + id + " no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            response.put("mensaje", "Ha ocurrido un error al intentar eliminar el docente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El docente se ha eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
