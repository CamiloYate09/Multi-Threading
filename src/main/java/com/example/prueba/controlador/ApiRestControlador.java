package com.example.prueba.controlador;


import com.example.prueba.Negocio.EmpleadoNegocio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.prueba.exception.ResourceNotFoundException;
import com.example.prueba.modelo.Empleado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.prueba.repositorio.EmpleadoRepository;

/**
 * Rest encargado de servir las solicitudes
 */
@RestController
@RequestMapping("api1")
public class ApiRestControlador {
    Logger logger = LoggerFactory.getLogger(ApiRestControlador.class);

    @Autowired
    private EmpleadoRepository empleadoRepository;

    /*
Crear Intereses Compuestos
* */
    @PostMapping(value = "/crearEmpleado" , consumes={"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado createInteres(@Validated @RequestBody Empleado empleado) throws JsonProcessingException, ResourceNotFoundException {
        logger.info("Ingreso al método createInteres"  + empleado);
        EmpleadoNegocio empleadoNegocio = new EmpleadoNegocio();
        Empleado  valor =   empleadoNegocio.crearEmpleado(empleado);
        return empleadoRepository.save(valor);

    }

}
