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

import com.example.projectproposal.Entity.Facultad;
import com.example.projectproposal.Service.IFacultadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proposal")
public class FacultadRestController {

    @Autowired
    private IFacultadService facultadService;

    // Método para obtener una Facultad por su ID junto con las Sedes asociadas
    @GetMapping("/facultadesn/{id}")
    public ResponseEntity<?> getFacultadWithSedes(@PathVariable("id") Long id) {
        Facultad facultad = facultadService.findFacultadWithSedes(id);
        if (facultad == null) {
            return new ResponseEntity<>("Facultad no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(facultad, HttpStatus.OK);
    }

    @GetMapping("/facultades")
    public List<Facultad> index() {
        return facultadService.findAll();
    }

    @GetMapping("/findByIdFacultad/{id}")
    public ResponseEntity<?> GetById(@PathVariable("id") Long id) {

        Facultad facultad = null;
        Map<String, Object> response = new HashMap<>(); // instancia de hashmap

        try {
            facultad = facultadService.findById(id); // consutar el objeto en la base de datos
        } catch (DataAccessException e) {
            // TODO: handle exception
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // si no se encuentra el objecto que se busca en la base de datos
        if (facultad == null) {
            response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" - no existe en la base de datosQ")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Facultad>(facultad, HttpStatus.OK);

    }

    @PostMapping("/createfacultad") // crea el endpoint
    public ResponseEntity<?> create(@Valid @RequestBody Facultad Facultad, BindingResult result) {

        Facultad FacultadNew = null;
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
        // manejo de errorees en la consulta en la base de datos
        try {
            FacultadNew = facultadService.save(Facultad);// metodo para guardar en la base datos
        } catch (DataAccessException e) {
            // TODO: handle exception
            response.put("mensaje", "Error al realizar la insersion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //
        response.put("mensaje", "El cliente ha sido creado con exito!");
        response.put("cliente", FacultadNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/findByNameFacultades/{nombreFacultad}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable("nombreFacultad") String nombreFacultad) {

        List<Facultad> Facultads;
        Map<String, Object> response = new HashMap<>(); // instancia de hashmap

        try {
            Facultads = facultadService.findByNombreFacultades(nombreFacultad); // consutar el objeto en la base de datos
            // si no se encuentra el objecto que se busca en la base de datos
            if (Facultads == null) {
                response.put("mensaje", "El cliente ID:".concat(nombreFacultad.concat(" - no existe en la base de datosQ")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<List<Facultad>>(Facultads, HttpStatus.OK);

        } catch (DataAccessException e) {
            // TODO: handle exception
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateFacultad/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Facultad facultad, BindingResult result) {
        // Facultad FacultadActual = FacultadService.findById(id); // se realiza la consulta para
        // encontrar la Facultad que esta en la base de datos

        Facultad FacultadUpdate = null;

        Map<String, Object> response = new HashMap<>(); // instancia de hashmap para crear un map para el manejo de
        // errores
        // valida si encontro algun error de las validaciones que anotamos en la clase
        // entity
        if (result.hasErrors()) {

            // Se crea una lista de errores (errores que vienen del front en formato json)
            List<String> errors = result.getFieldErrors().stream().map(
                    fieldError -> "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        try {
            // se agrega los nuevos datos ala Facultad que se trajo de la base de datos
            // FacultadActual.setNombreFacultad(Facultad.getNombreFacultad());
            // FacultadActual.setDireccion(Facultad.getDireccion());
            // FacultadActual.setTelefono(Facultad.getTelefono());
            // FacultadActual.setEmail(Facultad.getEmail());
            // metodo para guardar en la base datos
            // FacultadUpdate = FacultadService.save(FacultadActual);

            FacultadUpdate = facultadService.updateFacultad(facultad, id);

            // validacion para saber si encontro la Facultad en la base de datos
            if (FacultadUpdate == null) {

                response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" - no existe en la base de datosQ")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            // TODO: handle exception
            // manejo de exception de la base de datos

            response.put("mensaje", "Error al realizar la actualizacion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido actualizado con exito!");
        response.put("Facultad", FacultadUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        // el metodo sava de la clase crudRespository
        // se utiliza el mismo metodo que en
        // el crear para actualizar esto por
        // detra
        // se entiendo como merger , si el metodo trae un id este lo actuliza de lo
        // contraio crear el objeto en la base de datos
    }

    @DeleteMapping("/deteleFacultad/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Facultad FacultadActual = facultadService.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (FacultadActual == null) {
            response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" - no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            // metodo para eleminar una Facultad de la base de datos
            facultadService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            response.put("mensaje", "La Facultad con el ID " + id + " no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            // TODO: handle exception
            response.put("mensaje", "Error al realizar el eliminar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido eliminadoo con exito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
